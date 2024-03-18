package dev.joseluisgs.models


import import.NextEvolution
import import.PrevEvolution
import kotlinx.serialization.SerialName

data class Pokemon(
    val avgSpawns: Double,
    val candy: String,
    val candyCount: Int,
    val egg: Int,
    val height: Double,
    val id: Int,
    val img: String,
    val multipliers: List<Double>? = null,
    val name: String,
    val nextEvolution: List<NextEvolution>? = null,
    val num: String,
    val prevEvolution: List<PrevEvolution>? = null,
    @SerialName("spawn_chance")
    val spawnChance: Double,
    @SerialName("spawn_time")
    val spawnTime: String,
    @SerialName("type")
    val type: List<String>,
    @SerialName("weaknesses")
    val weaknesses: List<String>,
    @SerialName("weight")
    val weight: Double
)