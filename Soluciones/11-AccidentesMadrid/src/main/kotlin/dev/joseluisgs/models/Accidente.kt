package dev.joseluisgs.dev.joseluisgs.models

import java.time.LocalDate
import java.time.LocalTime

data class Accidente(
    val numExpediente: String,
    val fecha: LocalDate,
    val hora: LocalTime,
    val calle: String,
    val distrito: String,
    val tipoAccidente: String,
    val estadoMeteorologico: String,
    val tipoVehiculo: String,
    val tipoPersona: String,
    val rangoEdad: String,
    val sexo: Sexo,
    val lesividad: String,
    val positivoAlcohol: Boolean,
    val positivoDrogas: Boolean,
) {
    enum class Sexo {
        HOMBRE, MUJER, NO_ASIGNADO
    }
}
