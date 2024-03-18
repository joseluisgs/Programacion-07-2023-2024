package dev.joseluisgs.dto

data class PersonasDto(
    val id: String,
    val nombre: String,
    val createdAt: String,
    val tipo: String,
    val modulo: String?,
    val edad: String?
)
