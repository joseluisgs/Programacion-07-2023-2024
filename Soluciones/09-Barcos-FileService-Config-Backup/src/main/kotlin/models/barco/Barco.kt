package dev.joseluisgs.models.barco

import java.time.LocalDate

abstract class Barco(
    val matricula: String,
    val nombre: String,
    val patron: String,
    val cargaActual: Double,
    val fechaIncorporacion: LocalDate
)