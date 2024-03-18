package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

class StorageJson : Storage {
    private val file = Path("data", "alumnos.json")

    init {
        Files.createDirectories(Paths.get("data"))
    }

    override fun save(list: List<Alumno>) {
        val json = Json { prettyPrint = true; ignoreUnknownKeys = true }
        file.writeText(json.encodeToString<List<Alumno>>(list))
    }

    override fun load(): List<Alumno> {
        val json = Json { prettyPrint = true; ignoreUnknownKeys = true }
        return json.decodeFromString<List<Alumno>>(file.readText())
    }
}