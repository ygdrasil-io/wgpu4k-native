@file:OptIn(ExperimentalUnsignedTypes::class)

package org.graphiks.kffi.engine

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import org.graphiks.kffi.MemoryAllocator
import org.graphiks.kffi.MemoryBuffer
import org.graphiks.kffi.NativeAddress

/**
 * Exercice les wrappers struct-by-value du moteur (M5.2bis) contre la fixture C :
 * le layout "Box" est enregistré comme le fait le code généré (registerStructLayout),
 * puis les wrappers marshallent l'argument struct (reinterpret à la taille du layout)
 * et le retour struct (SegmentAllocator de l'allocateur appelant, convention FFM).
 */
class JvmDowncallEngineStructByValueTest : FreeSpec({

    beforeSpec {
        JvmDowncallEngine.registerStructLayout(
            "Box",
            sizeBytes = 8L,
            alignmentBytes = 4L,
            fields = listOf(
                JvmDowncallEngine.StructField("a", JvmDowncallEngine.FieldKind.INT32, 0L),
                JvmDowncallEngine.StructField("b", JvmDowncallEngine.FieldKind.INT32, 4L),
            ),
        )
    }

    "struct-by-value return copies the callee result into the caller allocator" {
        MemoryAllocator().use { allocator ->
            val result = JvmDowncallEngine.callStructReturnBox(
                JvmDowncallFixture.symbol("bench_make_box"),
                allocator,
                5,
            )
            val buffer = MemoryBuffer(result, 8uL)
            buffer.readInt(0uL) shouldBe 5
            buffer.readInt(4uL) shouldBe 6
        }
    }

    "struct-by-value argument is copied from the raw struct buffer" {
        MemoryAllocator().use { allocator ->
            val box = allocator.allocateBuffer(8uL)
            box.writeInt(20, 0uL)
            box.writeInt(22, 4uL)
            JvmDowncallEngine.callStructArgBox(
                JvmDowncallFixture.symbol("bench_consume_box"),
                box.handler.rawValue,
            )
            JvmDowncallEngine.callI0(JvmDowncallFixture.symbol("bench_consume_box_get")) shouldBe 42L
        }
    }

    "registry reproduces Clang offsets with explicit padding and trailing gap" {
        JvmDowncallEngine.registerStructLayout(
            "Padded",
            sizeBytes = 16L,
            alignmentBytes = 8L,
            fields = listOf(
                JvmDowncallEngine.StructField("c", JvmDowncallEngine.FieldKind.INT8, 0L),
                JvmDowncallEngine.StructField("__pad", JvmDowncallEngine.FieldKind.PADDING, 3L),
                JvmDowncallEngine.StructField("i", JvmDowncallEngine.FieldKind.INT32, 4L),
                JvmDowncallEngine.StructField("t", JvmDowncallEngine.FieldKind.INT64, 8L),
            ),
        )
        val layout = JvmDowncallEngine.structLayout("Padded")
        layout.byteOffset(java.lang.foreign.MemoryLayout.PathElement.groupElement("c")) shouldBe 0L
        layout.byteOffset(java.lang.foreign.MemoryLayout.PathElement.groupElement("i")) shouldBe 4L
        layout.byteOffset(java.lang.foreign.MemoryLayout.PathElement.groupElement("t")) shouldBe 8L
        layout.byteSize() shouldBe 16L
        layout.byteAlignment() shouldBe 8L
    }

    "registry resolves nested struct layouts through the STRUCT cName" {
        JvmDowncallEngine.registerStructLayout(
            "Inner",
            sizeBytes = 4L,
            alignmentBytes = 4L,
            fields = listOf(
                JvmDowncallEngine.StructField("x", JvmDowncallEngine.FieldKind.INT32, 0L),
            ),
        )
        JvmDowncallEngine.registerStructLayout(
            "Outer",
            sizeBytes = 12L,
            alignmentBytes = 4L,
            fields = listOf(
                JvmDowncallEngine.StructField("tag", JvmDowncallEngine.FieldKind.INT8, 0L),
                JvmDowncallEngine.StructField("__pad", JvmDowncallEngine.FieldKind.PADDING, 3L),
                JvmDowncallEngine.StructField("Inner", JvmDowncallEngine.FieldKind.STRUCT, 4L),
                JvmDowncallEngine.StructField("tail", JvmDowncallEngine.FieldKind.INT16, 8L),
                JvmDowncallEngine.StructField("__pad", JvmDowncallEngine.FieldKind.PADDING, 2L),
            ),
        )
        val layout = JvmDowncallEngine.structLayout("Outer")
        layout.byteOffset(java.lang.foreign.MemoryLayout.PathElement.groupElement("tag")) shouldBe 0L
        layout.byteOffset(java.lang.foreign.MemoryLayout.PathElement.groupElement("Inner")) shouldBe 4L
        layout.byteOffset(java.lang.foreign.MemoryLayout.PathElement.groupElement("tail")) shouldBe 8L
        layout.byteSize() shouldBe 12L
        layout.byteAlignment() shouldBe 4L
    }

    "registry rejects metadata whose field sizes disagree with the declared size" {
        JvmDowncallEngine.registerStructLayout(
            "Broken",
            sizeBytes = 8L,
            alignmentBytes = 4L,
            fields = listOf(
                JvmDowncallEngine.StructField("a", JvmDowncallEngine.FieldKind.INT32, 0L),
            ),
        )
        shouldThrow<IllegalStateException> {
            JvmDowncallEngine.structLayout("Broken")
        }
    }
})
