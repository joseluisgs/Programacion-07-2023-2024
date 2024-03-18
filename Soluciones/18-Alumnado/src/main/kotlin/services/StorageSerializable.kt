package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

class StorageSerializable : Storage {
    private val file = Path("data", "alumnos.bin")

    init {
        Files.createDirectories(Paths.get("data"))
    }

    override fun save(list: List<Alumno>) {
        ObjectOutputStream(file.outputStream()).use {
            list.forEach { al -> it.writeObject(al) }
        }

        // Otra es con la lista completa
        // ObjectOutputStream(file.outputStream()).use {
        //     it.writeObject(list)
        // }

    }

    override fun load(): List<Alumno> {
        val alumnos = mutableListOf<Alumno>()

        ObjectInputStream(file.inputStream()).use {
            while (it.available() > 0) {
                val al = it.readObject() as Alumno
                alumnos.add(al)
            }
        }
        return alumnos

        // Otra es con la lista completa
        // ObjectInputStream(file.inputStream()).use {
        //     return it.readObject() as List<Alumno>
        // }
    }
}