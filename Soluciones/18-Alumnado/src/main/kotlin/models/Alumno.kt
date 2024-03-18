package dev.joseluisgs.models

import kotlinx.serialization.Serializable

@Serializable
data class Alumno(val numero: Int, val nombre: String, val repetidor: Boolean) : java.io.Serializable

