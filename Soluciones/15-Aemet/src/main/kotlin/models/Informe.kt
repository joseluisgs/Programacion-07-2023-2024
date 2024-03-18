package dev.joseluisgs.models

import kotlinx.serialization.Serializable

@Serializable
data class Informe(
    val provincia: String,
    val temperaturaMediaMaxima: Double,
    val temperaturaMediaMinima: Double,
    val temperaturaMaxima: Double,
    val temperaturaMinima: Double,
    val precipitacionFechaNumLugaresMedia: List<Pair<String, Pair<Int, Double>>>
)