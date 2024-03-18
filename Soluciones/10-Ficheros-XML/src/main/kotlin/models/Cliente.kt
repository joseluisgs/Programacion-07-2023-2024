package dev.joseluisgs.models

import kotlinx.serialization.Serializable

@Serializable
data class Cliente(
    val nombre: String,
    val email: String,
)