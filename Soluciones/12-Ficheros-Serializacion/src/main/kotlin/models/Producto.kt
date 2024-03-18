package dev.joseluisgs.models

import java.io.Serializable

data class Producto(
    val nombre: String,
    val precio: Double,
    val stock: Long = 0,
) : Serializable