package dev.joseluisgs.models

import kotlinx.serialization.Serializable

@Serializable
data class Producto(
    val nombre: String,
    val precio: Double,
)