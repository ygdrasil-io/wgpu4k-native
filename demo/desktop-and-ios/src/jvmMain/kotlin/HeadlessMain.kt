package io.ygdrasil.wgpu

import ffi.LibraryLoader
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    LibraryLoader.load()
    configureLogs(WGPULogLevel_Warn)

    val image = renderHeadlessTriangle()
    val outputFile = File("build/headless/triangle.png")
    outputFile.parentFile.mkdirs()
    ImageIO.write(image.toBufferedImage(), "png", outputFile)
    println("Wrote ${outputFile.absolutePath}")
}

private fun HeadlessTriangleImage.toBufferedImage(): BufferedImage =
    BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).also { image ->
        for (y in 0 until height) {
            for (x in 0 until width) {
                val offset = (y * width + x) * 4
                val r = rgba[offset].toInt() and 0xFF
                val g = rgba[offset + 1].toInt() and 0xFF
                val b = rgba[offset + 2].toInt() and 0xFF
                val a = rgba[offset + 3].toInt() and 0xFF
                image.setRGB(x, y, (a shl 24) or (r shl 16) or (g shl 8) or b)
            }
        }
    }
