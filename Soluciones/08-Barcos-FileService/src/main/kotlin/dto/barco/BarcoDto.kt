package dev.joseluisgs.dto.barco

import kotlinx.serialization.Serializable

@Serializable
data class BarcoDto(
    val matricula: String,
    val nombre: String,
    val patron: String,
    val cargaActual: String,
    val fechaIncorporacion: String,
    val tipo: String,
    val capacidad: String?,
    val experiencia: String?,
    val activo: String?
)