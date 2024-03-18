package dev.joseluisgs.models

import java.io.Serializable

data class Cliente(
    val nombre: String,
    val email: String,
) : Serializable