package dev.joseluisgs.services.storage.barcos

import dev.joseluisgs.dto.barco.BarcoDto
import dev.joseluisgs.exceptions.barco.BarcoExceptions
import dev.joseluisgs.exceptions.storage.StorageExceptions
import dev.joseluisgs.mappers.barco.toBarco
import dev.joseluisgs.models.barco.Atunero
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.models.barco.Mantenimiento
import dev.joseluisgs.models.barco.Marisquero
import dev.joseluisgs.services.storage.base.FileStorage
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class BarcosFileStorageCsv : FileStorage<Barco> {
    override fun readFromFile(file: File): List<Barco> {
        logger.debug { "Leyendo de fichero CSV: $file" }
        if (!file.exists()) {
            logger.error { "El fichero no existe: $file" }
            throw StorageExceptions.FileNotFound("El fichero no existe: $file")
        }

        return file.readLines()
            .drop(1)
            .map {
                it.split(",")
            }.map {
                BarcoDto(
                    matricula = it[0],
                    nombre = it[1],
                    patron = it[2],
                    cargaActual = it[3],
                    fechaIncorporacion = it[4],
                    tipo = it[5],
                    capacidad = it[6],
                    experiencia = it[7],
                    activo = it[8]
                ).toBarco()
            }
    }


    override fun writeToFile(list: List<Barco>, file: File) {
        logger.debug { "Escribiendo en fichero CSV: $file" }
        file.writeText("Matricula,Nombre,Patron,Carga,Fecha,Tipo,Capacidad,Experiencia,Activo\n")
        list.forEach {
            file.appendText("${it.matricula},${it.nombre},${it.patron},${it.cargaActual},${it.fechaIncorporacion},")
            when (it) {
                is Atunero -> {
                    file.appendText("${"Atunero"},${it.cargaMaxima},,")
                }

                is Marisquero -> {
                    file.appendText("${"Marisquero"},,${it.experienciaTripulacion},")
                }

                is Mantenimiento -> {
                    file.appendText("${"Mantenimiento"},,,${it.activo}")
                }

                else -> throw BarcoExceptions.TypeNotFound(it::class.simpleName ?: "Barco")
            }
            file.appendText("\n")
        }
    }
}