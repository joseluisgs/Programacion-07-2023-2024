package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.appendText
import kotlin.io.path.readLines
import kotlin.io.path.writeText

class StorageCsv : Storage {
    private val file = Path("data", "alumnos.csv")

    init {
        Files.createDirectories(Paths.get("data"))
    }

    override fun save(list: List<Alumno>) {
        val cabezera = "numero,nombre,repetidor\n"
        file.writeText(cabezera)
        list.forEach {
            file.appendText("${it.numero},${it.nombre},${it.repetidor}\n")
        }
        // file.appendText(list.joinToString(separator = "\n") { "${it.numero},${it.nombre},${it.repetidor}")
    }

    override fun load(): List<Alumno> {
        val list = mutableListOf<Alumno>()
        /*return file.readLines()
            .drop(1)
            .map {
                it.split(",")
            }.map { it ->
                Alumno(it[0].toInt(), it[1], it[2].toBoolean())
            }*/
        return file.readLines().drop(1)
            .map {
                val (numero, nombre, repetirdor) = it.split(",")
                Alumno(numero.toInt(), nombre, repetirdor.toBoolean())
            }
    }
}