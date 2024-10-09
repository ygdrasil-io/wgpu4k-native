package generator

import domain.CLibraryModel
import generator.structure.toJnaStructure
import java.io.File

fun File.generateAndroidStructures(structures: List<CLibraryModel.Structure>) {
    structures.map(CLibraryModel.Structure::toJnaStructure)
        .forEach(::appendText)
}