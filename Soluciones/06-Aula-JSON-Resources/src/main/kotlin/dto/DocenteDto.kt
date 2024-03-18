package dev.joseluisgs.dto

import kotlinx.serialization.Serializable

@Serializable
data class DocenteDto(
    val nombre: String,
    val modulo: String
)