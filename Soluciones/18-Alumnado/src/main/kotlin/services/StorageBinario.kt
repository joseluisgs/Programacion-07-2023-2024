package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno
import java.io.RandomAccessFile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path

class StorageBinario : Storage {
    private val file = Path("data", "alumnos.dat")

    init {
        Files.createDirectories(Paths.get("data"))
    }

    override fun save(list: List<Alumno>) {
        val fileRandom = RandomAccessFile(file.toFile(), "rw")
        fileRandom.use {
            // fileRandom.seek(it.length()) // Posiciona el puntero al final del fichero
            // fileRandom.seek(0) // Posiciona el puntero al principio del fichero
            list.forEach { alumno ->
                fileRandom.writeInt(alumno.numero)
                fileRandom.writeUTF(alumno.nombre)
                fileRandom.writeBoolean(alumno.repetidor)
            }
        }

    }

    override fun load(): List<Alumno> {
        val fileRandom = RandomAccessFile(file.toFile(), "r")
        val alumnos = mutableListOf<Alumno>()
        fileRandom.use {
            // fileRandom.seek(0)
            while (it.filePointer < it.length()) {
                val numero = it.readInt() // 4 bytes
                val nombre = it.readUTF() // 2 bytes + longitud
                val repetidor = it.readBoolean() // 1 byte
                // alumnos.add(Alumno(numero, nombre, repetidor))
            }
        }
        return alumnos
    }
}