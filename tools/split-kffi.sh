#!/usr/bin/env bash
# tools/split-kffi.sh — M4.1: extraire le sous-arbre kffi vers Graphiks-org/kffi
#
# Extrait les 5 modules kffi (kffi, kffi-benchmark-spi, kffi-benchmark-jvm,
# kffi-benchmark-native, kffi-benchmark-android) du repo hôte wgpu4k-native et
# reconstruit un repo cible autonome Graphiks-org/kffi dans un répertoire
# temporaire :
#
#   1. crée une branche temporaire `kffi-split` depuis HEAD (hôte)
#   2. `git subtree split --prefix=<module>` par module → branches kffi-split-<module>
#   3. clone le repo cible dans un répertoire temporaire
#   4. `git subtree add --prefix=<module>` pour chaque module (histoire préservée)
#   5. écrit/adapte les fichiers racine du repo cible
#      (settings, build.gradle.kts, gradle.properties, wrapper, buildSrc,
#       version catalog, .gitignore, LICENSE, README+docs, workflows)
#   6. exclut kffi/benchmarks/results (chemins absolus hôte) via git rm
#   7. applique le fix F1 (coordonnées org.graphiks) aux 4 modules benchmark
#   8. vérifie le build du repo cible (compile + jvmTest + benchmarks)
#
# SÛRETÉ : le script n'écrit JAMAIS dans le worktree hôte — uniquement des
# branches locales (kffi-split*) et un répertoire temporaire. Les 5 dossiers
# modules restent dans le repo hôte jusqu'à M4.3. Le push vers GitHub est M4.4
# (confirmation utilisateur) — ce script ne pousse pas.
#
# HISTORIQUE (décision assumée — duplication de racines) :
# le repo cible a un "Initial commit" template (0768833) ; l'import subtree
# greffe les histoires réécrites des 5 modules (racines 06a4cc4, b43cb64,
# 2056d2a — SHAs déterministes, identiques pour toute re-split du même HEAD).
# Le graphe résultant a donc plusieurs racines (template + histoires modules).
# DÉCISION : accepter la duplication — les arbres modules sont disjoints (pas
# de bloat d'objets), l'historique par fichier est propre, et réécrire
# l'historique déjà poussé du repo cible (force-push) est exclu. Un re-split
# depuis zéro ne ferait que dupliquer le stockage d'objets sans gain
# d'historique.
#
# Options (env) :
#   KFFI_TARGET_URL   URL du repo cible (défaut https://github.com/Graphiks-org/kffi.git)
#   KFFI_WORKDIR      répertoire de travail (défaut mktemp -d)
#   KFFI_SKIP_VERIFY  =1 pour sauter la vérification du build (défaut : exécutée)
#
# Usage : bash tools/split-kffi.sh

set -euo pipefail

KFFI_TARGET_URL="${KFFI_TARGET_URL:-https://github.com/Graphiks-org/kffi.git}"
KFFI_MODULES=(kffi kffi-benchmark-spi kffi-benchmark-jvm kffi-benchmark-native kffi-benchmark-android)
KFFI_MARKER_BRANCH="kffi-split"
KFFI_VERIFY_TASKS=(
	:kffi:compileKotlinJvm
	:kffi:compileDebugKotlinAndroid
	:kffi:compileKotlinMacosArm64
	:kffi:jvmTest
	:kffi-benchmark-spi:compileKotlinJvm
	:kffi-benchmark-spi:jvmTest
	:kffi-benchmark-jvm:compileKotlin
	:kffi-benchmark-native:compileKotlinMacosArm64
	:kffi-benchmark-android:assembleDebugAndroidTest
)

log() { printf '[kffi-split] %s\n' "$*"; }
die() { printf '[kffi-split] ERROR: %s\n' "$*" >&2; exit 1; }

command -v git >/dev/null || die "git introuvable"
git subtree split -h >/dev/null 2>&1 || die "git subtree indisponible"
command -v python3 >/dev/null || die "python3 introuvable (requis pour les adaptations de fichiers)"

HOST_REPO="$(git rev-parse --show-toplevel 2>/dev/null)" || die "ce script doit être lancé depuis le repo hôte"
log "repo hôte : $HOST_REPO"

if [[ -n "${KFFI_WORKDIR:-}" ]]; then
	WORKDIR="$KFFI_WORKDIR"
	mkdir -p "$WORKDIR"
	log "répertoire de travail : $WORKDIR (fourni)"
else
	WORKDIR="$(mktemp -d "${TMPDIR:-/tmp}/kffi-split.XXXXXX")"
	log "répertoire de travail : $WORKDIR"
fi
TARGET_DIR="$WORKDIR/kffi-target"

# ---------------------------------------------------------------- phase 1 : split (hôte)
log "phase 1 — branches de split dans le repo hôte"

# fraîcheur du marqueur : la branche kffi-split doit pointer sur HEAD.
# Si le hôte a avancé depuis le dernier split, tout est re-fait.
head_sha="$(git -C "$HOST_REPO" rev-parse HEAD)"
if git -C "$HOST_REPO" rev-parse --verify --quiet "refs/heads/$KFFI_MARKER_BRANCH" >/dev/null; then
	marker_head="$(git -C "$HOST_REPO" rev-parse "refs/heads/$KFFI_MARKER_BRANCH")"
	if [[ "$marker_head" != "$head_sha" ]]; then
		log "branche $KFFI_MARKER_BRANCH périmée (HEAD avancé) — suppression des branches kffi-split* et re-split complet"
		for module in "${KFFI_MODULES[@]}"; do
			git -C "$HOST_REPO" branch -D "kffi-split-$module" >/dev/null 2>&1 || true
		done
		git -C "$HOST_REPO" branch -D "$KFFI_MARKER_BRANCH" >/dev/null 2>&1 || true
	fi
fi
git -C "$HOST_REPO" rev-parse --verify --quiet "refs/heads/$KFFI_MARKER_BRANCH" >/dev/null \
	|| git -C "$HOST_REPO" branch "$KFFI_MARKER_BRANCH" HEAD
log "branche marqueur $KFFI_MARKER_BRANCH prête (depuis HEAD)"

for module in "${KFFI_MODULES[@]}"; do
	split_branch="kffi-split-$module"
	needs_split=false
	if git -C "$HOST_REPO" rev-parse --verify --quiet "refs/heads/$split_branch" >/dev/null; then
		# fraîcheur par module : l'arbre du tip de la branch de split doit être
		# identique à l'arbre du module à HEAD. subtree split est déterministe
		# (mêmes entrées → mêmes SHAs), donc l'égalité d'arbres ⇒ split à jour.
		split_tree="$(git -C "$HOST_REPO" rev-parse "refs/heads/$split_branch^{tree}")"
		module_tree="$(git -C "$HOST_REPO" rev-parse "HEAD:$module")"
		if [[ "$split_tree" == "$module_tree" ]]; then
			log "branch $split_branch à jour (arbre = HEAD:$module) — réutilisée"
		else
			log "branch $split_branch périmée (arbre ≠ HEAD:$module) — re-split"
			git -C "$HOST_REPO" branch -D "$split_branch" >/dev/null 2>&1 || true
			needs_split=true
		fi
	else
		needs_split=true
	fi
	if [[ "$needs_split" == true ]]; then
		log "git subtree split --prefix=$module → $split_branch"
		git -C "$HOST_REPO" subtree split --prefix="$module" --branch="$split_branch"
	fi
	git -C "$HOST_REPO" rev-parse --verify --quiet "refs/heads/$split_branch" >/dev/null \
		|| die "échec du split de $module (branch $split_branch absente)"
done

# ---------------------------------------------------------------- phase 2 : clone cible
log "phase 2 — clone du repo cible $KFFI_TARGET_URL"
if [[ -d "$TARGET_DIR" ]]; then
	origin="$(git -C "$TARGET_DIR" config --get remote.origin.url 2>/dev/null || true)"
	if [[ "$origin" != "$KFFI_TARGET_URL" ]]; then
		die "le répertoire $TARGET_DIR existe mais n'est pas un clone de $KFFI_TARGET_URL (remote.origin.url : ${origin:-absente}) — suppression refusée"
	fi
	log "clone précédent du repo cible détecté dans $TARGET_DIR — suppression"
	rm -rf "$TARGET_DIR"
fi
git clone "$KFFI_TARGET_URL" "$TARGET_DIR" || die "clone du repo cible impossible"

# ---------------------------------------------------------------- phase 3 : subtree add
log "phase 3 — import des modules dans le repo cible (histoire préservée)"
for module in "${KFFI_MODULES[@]}"; do
	if git -C "$TARGET_DIR" ls-files --error-unmatch "$module" >/dev/null 2>&1; then
		die "le repo cible contient déjà $module/ — suppression manuelle requise"
	fi
	log "git subtree add --prefix=$module"
	git -C "$TARGET_DIR" fetch "$HOST_REPO" "refs/heads/kffi-split-$module:refs/kffi-split/$module"
	git -C "$TARGET_DIR" subtree add --prefix="$module" "refs/kffi-split/$module" \
		-m "chore: import $module (subtree split from wgpu4k-native)"
done

# ---------------------------------------------------------------- phase 4 : fichiers racine
log "phase 4 — fichiers racine du repo cible (adaptés du repo hôte)"

mkdir -p "$TARGET_DIR/gradle/wrapper" "$TARGET_DIR/.github/workflows" "$TARGET_DIR/docs"

# settings.gradle.kts — rootProject.name = "kffi", uniquement les 5 modules
cat > "$TARGET_DIR/settings.gradle.kts" <<'KT'
rootProject.name = "kffi"

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
	plugins {
		id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}

include("kffi")
include("kffi-benchmark-spi")
include("kffi-benchmark-jvm")
include("kffi-benchmark-native")
include("kffi-benchmark-android")
KT

# build.gradle.kts — group org.graphiks, chaîne de version kffi (M2.4/F7),
# cleanPublicationVerificationRepository (requis par le plugin publish),
# verifyPublicationMetadata limité aux modules qui publient (:kffi, :kffi-benchmark-spi)
cat > "$TARGET_DIR/build.gradle.kts" <<'KT'
import com.google.gson.JsonParser

allprojects {

	repositories {
		mavenLocal()
		google()
		mavenCentral()
	}

	group = "org.graphiks"
}

val kffiVersion = providers.gradleProperty("kffi.version")
	.orElse(providers.environmentVariable("KFFI_VERSION"))
	.orElse("1.0.0-SNAPSHOT")
	.map { it.trim().ifEmpty { "1.0.0-SNAPSHOT" } }

subprojects {
	version = kffiVersion.get()
}

val publicationVerificationRepository = layout.buildDirectory
	.dir("publication-verification/repository")

val cleanPublicationVerificationRepository by tasks.registering(Delete::class) {
	delete(publicationVerificationRepository)
}

val verifyPublicationMetadata by tasks.registering {
	group = "verification"
	description = "Publishes the kffi publications to a local repository and verifies their metadata."
	dependsOn(
		":kffi:publishAllPublicationsToPublicationVerificationRepository",
		":kffi-benchmark-spi:publishAllPublicationsToPublicationVerificationRepository",
	)
	doLast {
		val repository = publicationVerificationRepository.get().asFile
		val kffiGroup = "org.graphiks"
		val coordinatePath = kffiGroup.replace('.', '/')
		val publishedVersion = kffiVersion.get()
		fun uniqueModuleFile(artifact: String): File {
			val versionDirectory = repository.resolve("$coordinatePath/$artifact/$publishedVersion")
			val candidates = versionDirectory.listFiles { file ->
				file.isFile && file.name.startsWith("$artifact-") && file.name.endsWith(".module")
			}?.toList().orEmpty()
			require(candidates.size == 1) {
				"Expected exactly one $artifact .module file in $versionDirectory, " +
					"but found ${candidates.map { it.name }.sorted()}"
			}
			return candidates.single()
		}
		fun verifyCoordinates(artifact: String) {
			val metadataFile = uniqueModuleFile(artifact)
			val root = JsonParser.parseString(metadataFile.readText()).asJsonObject
			val group = root.get("group").asString
			val version = root.get("version").asString
			require(group == kffiGroup) {
				"Expected $artifact metadata group $kffiGroup, but found $group"
			}
			require(version == publishedVersion) {
				"Expected $artifact metadata version $publishedVersion, but found $version"
			}
		}
		verifyCoordinates("kffi-jvm")
		verifyCoordinates("kffi-benchmark-spi-jvm")
	}
}
KT

# gradle.properties — copié, retrait de wgpu.base.url (usage hôte uniquement)
sed '/wgpu\.base\.url/d' "$HOST_REPO/gradle.properties" > "$TARGET_DIR/gradle.properties"

# wrapper, version catalog, buildSrc, gradlew, .gitignore, LICENSE — copiés tels quels
cp "$HOST_REPO/gradle/wrapper/gradle-wrapper.jar" "$TARGET_DIR/gradle/wrapper/"
cp "$HOST_REPO/gradle/wrapper/gradle-wrapper.properties" "$TARGET_DIR/gradle/wrapper/"
cp "$HOST_REPO/gradle/libs.versions.toml" "$TARGET_DIR/gradle/"
cp "$HOST_REPO/gradlew" "$TARGET_DIR/"
cp "$HOST_REPO/gradlew.bat" "$TARGET_DIR/"
# buildSrc — copié sans les artefacts locaux (build/, .gradle/, .kotlin/, junk JVM)
rsync -a \
	--exclude 'build/' --exclude '.gradle/' --exclude '.kotlin/' \
	--exclude 'hs_err_pid*.log' --exclude 'replay_pid*.log' \
	"$HOST_REPO/buildSrc/" "$TARGET_DIR/buildSrc/"
cp "$HOST_REPO/.gitignore" "$TARGET_DIR/"
cp "$HOST_REPO/LICENSE" "$TARGET_DIR/"

# README + docs — kffi-consumer-doc devient le README racine du repo cible
cp "$HOST_REPO/kffi-consumer-doc/README.md" "$TARGET_DIR/README.md"
cp "$HOST_REPO/kffi-consumer-doc/docs/quickstart.md" "$TARGET_DIR/docs/"

# kffi-publish-snapshots.yml — adapté : KFFI_VERSION (F7), pas de submodule cible
cat > "$TARGET_DIR/.github/workflows/kffi-publish-snapshots.yml" <<'YAML'
name: kffi-publish-snapshots
on:
  push:
    branches: [main]

jobs:
  publish:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 25
      - name: Publish snapshots
        env:
          ORG_GRADLE_PROJECT_mavenCentralUsername: ${{ secrets.MAVEN_CENTRAL_USERNAME }}
          ORG_GRADLE_PROJECT_mavenCentralPassword: ${{ secrets.MAVEN_CENTRAL_PASSWORD }}
          ORG_GRADLE_PROJECT_signingInMemoryKey: ${{ secrets.SIGNING_KEY }}
          ORG_GRADLE_PROJECT_signingInMemoryKeyPassword: ${{ secrets.SIGNING_PASSWORD }}
        run: |
          export KFFI_VERSION="$(date +%Y%m%d%H%M%S)-SNAPSHOT"
          ./gradlew :kffi:publishAllPublicationsToMavenCentral \
            :kffi-benchmark-spi:publishAllPublicationsToMavenCentral
YAML

# kffi-benchmark-ci.yml — copié tel quel (submodules: recursive inoffensif)
cp "$HOST_REPO/.github/workflows/kffi-benchmark-ci.yml" "$TARGET_DIR/.github/workflows/"

# kffi-test.yml — extrait des jobs kffi du test.yml hôte (jobs wgpu retirés)
cat > "$TARGET_DIR/.github/workflows/kffi-test.yml" <<'YAML'
name: kffi-test

on:
  push:
    branches: [main]
  pull_request:

concurrency:
  group: ${{ github.workflow }}-${{ github.event.pull_request.number || github.ref }}
  cancel-in-progress: true

jobs:
  tests:
    strategy:
      fail-fast: false
      matrix:
        os: [ macos-latest, ubuntu-latest, windows-latest ]
    runs-on: ${{ matrix.os }}
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: 25
          cache: 'gradle'
      - name: Cache Gradle packages
        uses: actions/cache@v4
        with:
          path: ~/.gradle/caches
          key: ${{ runner.os }}-gradle-${{ hashFiles('**/*.gradle.kts') }}
          restore-keys: ${{ runner.os }}-gradle
      - name: Activate MSVC
        if: matrix.os == 'windows-latest'
        uses: ilammy/msvc-dev-cmd@v1
      - name: Run JVM tests
        run: ./gradlew :kffi:jvmTest
      - name: Compile Apple Native callback token codecs
        if: matrix.os == 'macos-latest'
        run: >-
          ./gradlew
          :kffi:compileKotlinIosX64 :kffi:compileKotlinIosArm64 :kffi:compileKotlinIosSimulatorArm64
          :kffi:compileKotlinMacosArm64 :kffi:compileKotlinMacosX64
      - name: Compile Linux Native callback token codecs
        if: matrix.os == 'ubuntu-latest'
        run: >-
          ./gradlew
          :kffi:compileKotlinLinuxX64 :kffi:compileKotlinLinuxArm64
      - name: Compile MinGW callback token codecs
        if: matrix.os == 'windows-latest'
        run: ./gradlew :kffi:compileKotlinMingwX64
      - name: Run macOS Native tests
        if: matrix.os == 'macos-latest'
        run: |
          if [[ "$(uname -m)" == "arm64" ]]; then
            ./gradlew :kffi:macosArm64Test
          else
            ./gradlew :kffi:macosX64Test
          fi
      - name: Run Linux Native tests
        if: matrix.os == 'ubuntu-latest'
        run: ./gradlew :kffi:linuxX64Test
      - name: Run Windows Native tests
        if: matrix.os == 'windows-latest'
        run: ./gradlew :kffi:mingwX64Test
YAML

# ---------------------------------------------------------------- phase 5 : commits cibles
log "phase 5 — commits dans le repo cible (pas de push)"
git -C "$TARGET_DIR" add -A
git -C "$TARGET_DIR" commit -m "chore: adapt root build files for standalone kffi repository"

# purge des fichiers template org (pas produits par l'adaptation) — cf. en-tête :
# workflows template (échec CI à chaque push), issue/PR templates, politique de
# contribution, conventions buildSrc io.ygdrasil, module d'exemple shared/,
# scripts/, docs du site. README.md et LICENSE sont conservés (écrasés par
# l'adaptation : README kffi-consumer-doc, licence MIT du hôte).
log "purge des fichiers template org (workflows, issue/PR templates, shared/, scripts/, docs, conventions buildSrc)"
git -C "$TARGET_DIR" rm -q -r --ignore-unmatch \
	.github/ISSUE_TEMPLATE \
	.github/PULL_REQUEST_TEMPLATE.md \
	.github/contributing-policy.toml \
	.github/scripts \
	.github/workflows/ci.yml \
	.github/workflows/docs.yml \
	.github/workflows/pr-policy.yml \
	.github/workflows/publish.yml \
	CHANGELOG.md \
	CODE_OF_CONDUCT.md CODE_OF_CONDUCT.fr.md \
	CONTRIBUTING.md \
	SECURITY.md SECURITY.fr.md \
	SUPPORT.md SUPPORT.fr.md \
	scripts \
	shared \
	buildSrc/gradle.properties \
	buildSrc/src/main/kotlin/ygdrasil \
	docs/build.gradle.kts \
	docs/mkdocs.yml \
	docs/docs
# workflow template inattendu dans le repo cible
for wf in "$TARGET_DIR"/.github/workflows/*.yml; do
	[[ -e "$wf" ]] || continue
	case "$(basename "$wf")" in
		kffi-*) ;;
		*) log "workflow template inattendu : $(basename "$wf") — purgé"
			git -C "$TARGET_DIR" rm -q "$wf" ;;
	esac
done
# défensif : aucun résidu template (liste à mettre à jour si le template change)
if git -C "$TARGET_DIR" ls-files | grep -qE '^(shared/|scripts/|CHANGELOG\.md|CODE_OF_CONDUCT|CONTRIBUTING\.md|SECURITY|SUPPORT|\.github/(ISSUE_TEMPLATE/|PULL_REQUEST_TEMPLATE\.md|contributing-policy\.toml|scripts/|workflows/(ci|docs|pr-policy|publish)\.yml)|buildSrc/gradle\.properties|buildSrc/src/main/kotlin/ygdrasil/|docs/(build\.gradle\.kts|mkdocs\.yml|docs/))'; then
	die "des fichiers template org subsistent après purge — liste du template à jour ?"
fi
# le catalog gradle/libs.versions.toml est copié du hôte et contient
# légitimement des entrées io.ygdrasil (glfw-native, rococoa, …) — exclu
if git -C "$TARGET_DIR" grep -l 'io\.ygdrasil' -- . ':!gradle/libs.versions.toml' >/dev/null 2>&1; then
	die "résidus io.ygdrasil détectés dans le repo cible après purge"
fi
git -C "$TARGET_DIR" add -A
git -C "$TARGET_DIR" commit -F - <<'MSG'
chore: remove org template leftovers

The Graphiks-org/kffi template "Initial commit" (0768833) carried generic
org scaffolding that the split adaptation does not produce: template CI
workflows (ci/docs/pr-policy/publish), issue/PR templates, contributing
policy, buildSrc conventions under io.ygdrasil, a sample `shared/` KMP
module and `scripts/`. Keeping them would break CI on every push (the
template workflows would run and fail) and would leak the io.ygdrasil
namespace into the kffi repository. README.md and LICENSE are kept — they
are overwritten by the split adaptation (kffi-consumer-doc README, host
MIT license).

History note (accepted duplication): the target repository already has a
template root (0768833) and the subtree imports bring the rewritten module
histories (roots 06a4cc4, b43cb64, 2056d2a — deterministic SHAs). The
graph therefore has multiple roots. This is accepted: the module file
history is clean, the module trees are disjoint (no object bloat), and
rewriting the target's already-pushed history would require a force-push
that is out of scope.
MSG

# exclusion kffi/benchmarks/results (chemins absolus hôte dans les JSON)
git -C "$TARGET_DIR" rm -r -q kffi/benchmarks/results
git -C "$TARGET_DIR" commit -m "chore: drop host benchmark result artifacts (absolute host paths)"

# fix F1 — coordonnées org.graphiks ré-affirmées post-évaluation dans les 4 modules benchmark
F1_SNIPPET=$(cat <<'KT'

val kffiVersion = providers.gradleProperty("kffi.version")
    .orElse(providers.environmentVariable("KFFI_VERSION"))
    .orElse("1.0.0-SNAPSHOT")
    .map { it.trim().ifEmpty { "1.0.0-SNAPSHOT" } }

afterEvaluate {
    // F1: vanniktech freezes groupId/version at plugin-apply time; re-assert
    // them post-evaluation so the kffi version chain wins (mirror kffi/build.gradle.kts).
    (extensions.findByName("publishing") as? PublishingExtension)?.publications
        ?.withType<MavenPublication>()
        ?.all {
            groupId = "org.graphiks"
            version = kffiVersion.get()
        }
}
KT
)
for module in kffi-benchmark-spi kffi-benchmark-jvm kffi-benchmark-native kffi-benchmark-android; do
	build_file="$TARGET_DIR/$module/build.gradle.kts"
	python3 - "$build_file" "$F1_SNIPPET" <<'PY'
import sys

path, snippet = sys.argv[1], sys.argv[2]
with open(path) as f:
    lines = f.readlines()
out = []
done = False
for line in lines:
    out.append(line)
    if not done and line.rstrip("\n") == 'group = "org.graphiks"':
        out.append(snippet)
        done = True
if not done:
    sys.exit(f"no 'group = \"org.graphiks\"' anchor found in {path}")
with open(path, "w") as f:
    f.writelines(out)
PY
done
git -C "$TARGET_DIR" add -A
git -C "$TARGET_DIR" commit -m "fix(publish): assert org.graphiks coordinates in kffi-benchmark modules (F1)"

# ---------------------------------------------------------------- phase 6 : vérification build
if [[ "${KFFI_SKIP_VERIFY:-}" == "1" ]]; then
	log "phase 6 — vérification du build SAUTÉE (KFFI_SKIP_VERIFY=1)"
else
	log "phase 6 — vérification du build du repo cible (peut prendre plusieurs minutes)"
	git -C "$TARGET_DIR" log --oneline -8
	git -C "$TARGET_DIR" status --short
	(
		cd "$TARGET_DIR"
		./gradlew "${KFFI_VERIFY_TASKS[@]}"
	) || die "build du repo cible en échec — voir la sortie Gradle ci-dessus"
	log "phase 6 — BUILD SUCCESSFUL : le repo cible est autonome"
fi

# ---------------------------------------------------------------- rapport final
log "==="
log "split terminé — repo cible prêt (PAS poussé) : $TARGET_DIR"
log "  branche : $(git -C "$TARGET_DIR" branch --show-current)"
log "  historique :"
git -C "$TARGET_DIR" log --oneline -12 | sed 's/^/    /'
log "prochaines étapes :"
log "  1. inspection : cd $TARGET_DIR && git status && ./gradlew :kffi:jvmTest"
log "  2. M4.2 : publication (publishAllPublicationsToMavenCentral, credentials)"
log "  3. M4.4 (confirmation utilisateur) : push de master (branche par défaut → main, F2)"
log "==="
