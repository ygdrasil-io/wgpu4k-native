#ifndef KFFI_CONSUMER_H
#define KFFI_CONSUMER_H

/*
 * Header minimal du consumer test : transformé par kextract en bindings KMP
 * (src/commonMain|jvmMain|nativeMain). Les formes sont choisies dans la table
 * actuelle de JvmDowncallEngine :
 *   - int → int                    (scalaire 32 bits)
 *   - const unsigned char* → int   (pointeur vers scalaire)
 *   - const unsigned char* → void  (pointeur entrant)
 *   - long long* → void            (64 bits via out-param mémoire)
 * NOTE :
 *   - `long` (LP64/LLP64) est rejeté par kextract ; largeurs variables
 *     interdites (voir la sanitization wgpu4k-native).
 *   - `const char*` nu est évité : kextract l'expose en CString (JVM) mais
 *     le cinterop Kotlin/Native le mappe en String? — les bindings native
 *     générés ne compilent pas. `const unsigned char*` passe par le pointeur
 *     brut (UByteVar) des deux côtés.
 */

int consumer_negate(int x);
int consumer_strlen(const unsigned char* s);
void consumer_set_string(const unsigned char* s);
void consumer_get_ping(long long* out);

#endif
