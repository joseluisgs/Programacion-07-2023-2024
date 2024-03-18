package dev.joseluisgs.dto

import kotlinx.serialization.Serializable

@Serializable
data class EstudianteDto(
    val nombre: String,
    val edad: String
)