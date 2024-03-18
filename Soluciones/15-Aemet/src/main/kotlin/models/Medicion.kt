package dev.joseluisgs.models

import java.time.LocalDate
import java.time.LocalTime

data class Medicion(
    val ciudad: String,
    val provincia: String,
    val fecha: LocalDate,
    val temperaturaMaxima: Double,
    val horaTemperaturaMaxima: LocalTime,
    val temperaturaMinima: Double,
    val horaTemperaturaMinima: LocalTime,
    val precipitacion: Double,
)