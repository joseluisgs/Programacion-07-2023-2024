package dev.joseluisgs.models

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Modelo de Vehiculo
 */
data class Vehiculo(
    val matricula: String,
    val marca: String,
    val modelo: String,
    val color: String,
    val precio: Double,
    val fechaMatriculacion: LocalDate,
    val vendido: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)