package dev.joseluisgs.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehiculoDto(
    val matricula: String,
    val marca: String,
    val modelo: String,
    val color: String,
    val precio: String,
    @SerialName("fecha_matriculacion")
    val fechaMatriculacion: String,
    val vendido: String,
    val createdAt: String,
    val updatedAt: String,
)
