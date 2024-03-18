package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

class StorageXml : Storage {
    private val file = Path("data", "alumnos.xml")

    init {
        Files.createDirectories(Paths.get("data"))
    }

    override fun save(list: List<Alumno>) {
        val xml = XML { indent = 4 }
        file.writeText(xml.encodeToString<List<Alumno>>(list))
    }

    override fun load(): List<Alumno> {
        val xml = XML { }
        return xml.decodeFromString<List<Alumno>>(file.readText())
    }
}