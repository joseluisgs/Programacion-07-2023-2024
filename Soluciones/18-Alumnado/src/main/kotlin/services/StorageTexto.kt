package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.appendText
import kotlin.io.path.readLines
import kotlin.io.path.writeText

class StorageTexto : Storage {
    private val file = Path("data", "alumnos.txt")

    init {
        Files.createDirectories(Paths.get("data"))
    }

    override fun save(list: List<Alumno>) {
        // Forma rapida
        //val data = list.joinToString("\n") { "${it.numero},${it.nombre},${it.repetidor}" }
        // file.writeText(data)
        // uno por uno
        //list.forEach {
        //    file.appendText("${it.numero},${it.nombre},${it.repetidor}\n")
        //}
        // Una línea por dato
        file.writeText("")
        list.forEach {
            file.appendText("${it.numero}\n")
            file.appendText("${it.nombre}\n")
            file.appendText("${it.repetidor}\n")
        }
    }

    override fun load(): List<Alumno> {
        val list = mutableListOf<Alumno>()
        /*file.bufferedReader().use {
            // Mientras haya una línea
            while (it.ready()) {
                val numero = it.readLine().toInt()
                val nombre = it.readLine()
                val repetidor = it.readLine().toBoolean()
                list.add(Alumno(numero, nombre, repetidor))
            }
        }*/
        // O con windowed(3, 3) o chunked(3)
        return file.readLines().chunked(3).map {
            Alumno(it[0].toInt(), it[1], it[2].toBoolean())
        }
    }
}