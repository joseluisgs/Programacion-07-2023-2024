package dev.joseluisgs.services.storage.barco

import dev.joseluisgs.dto.barco.BarcoDto
import dev.joseluisgs.exceptions.storage.StorageExceptions
import dev.joseluisgs.mappers.barco.toBarco
import dev.joseluisgs.mappers.barco.toBarcoDto
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.services.storage.base.FileStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class BarcosFileStorageJson : FileStorage<Barco> {
    override fun readFromFile(file: File): List<Barco> {
        logger.debug { "Leyendo de fichero JSON: $file" }
        if (!file.exists()) {
            logger.error { "El fichero no existe: $file" }
            throw StorageExceptions.FileNotFound("El fichero no existe: $file")
        }

        return Json { ignoreUnknownKeys = true }
            .decodeFromString<List<BarcoDto>>(file.readText())
            .map {
                it.toBarco()
            }
    }

    override fun writeToFile(list: List<Barco>, file: File) {
        logger.debug { "Escribiendo en fichero JSON: $file" }
        file.writeText(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }.encodeToString<List<BarcoDto>>(
                list.map { it.toBarcoDto() }
            )
        )
    }
}