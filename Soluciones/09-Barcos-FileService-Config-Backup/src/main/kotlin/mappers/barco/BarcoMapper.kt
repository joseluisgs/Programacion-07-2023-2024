package dev.joseluisgs.mappers.barco

import dev.joseluisgs.dto.barco.BarcoDto
import dev.joseluisgs.exceptions.barco.BarcoExceptions
import dev.joseluisgs.models.barco.Atunero
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.models.barco.Mantenimiento
import dev.joseluisgs.models.barco.Marisquero
import java.time.LocalDate

fun BarcoDto.toBarco(): Barco {
    return when (this.tipo) {
        "Atunero" -> Atunero(
            this.matricula,
            this.nombre,
            this.patron,
            this.cargaActual.toDouble(),
            LocalDate.parse(this.fechaIncorporacion),
            this.capacidad?.toDouble() ?: 0.0,
        )

        "Marisquero" -> Marisquero(
            this.matricula,
            this.nombre,
            this.patron,
            this.cargaActual.toDouble(),
            LocalDate.parse(this.fechaIncorporacion),
            this.experiencia?.toInt() ?: 0,
        )

        "Mantenimiento" -> Mantenimiento(
            this.matricula,
            this.nombre,
            this.patron,
            this.cargaActual.toDouble(),
            LocalDate.parse(this.fechaIncorporacion),
            this.activo?.toBoolean() ?: false,
        )

        else -> throw BarcoExceptions.TypeNotFound(this.tipo)
    }
}

fun Barco.toBarcoDto(): BarcoDto {
    return when (this) {
        is Atunero -> BarcoDto(
            this.matricula,
            this.nombre,
            this.patron,
            this.cargaActual.toString(),
            this.fechaIncorporacion.toString(),
            "Atunero",
            this.cargaMaxima.toString(),
            null,
            null
        )

        is Marisquero -> BarcoDto(
            this.matricula,
            this.nombre,
            this.patron,
            this.cargaActual.toString(),
            this.fechaIncorporacion.toString(),
            "Marisquero",
            null,
            this.experienciaTripulacion.toString(),
            null
        )

        is Mantenimiento -> BarcoDto(
            this.matricula,
            this.nombre,
            this.patron,
            this.cargaActual.toString(),
            this.fechaIncorporacion.toString(),
            "Mantenimiento",
            null,
            null,
            this.activo.toString()
        )

        else -> {
            throw BarcoExceptions.TypeNotFound(this::class.simpleName ?: "Barco")
        }
    }
}