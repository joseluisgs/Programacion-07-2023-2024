package import


import dev.joseluisgs.models.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("avg_spawns")
    val avgSpawns: Double,
    @SerialName("candy")
    val candy: String,
    @SerialName("candy_count")
    val candyCount: Int? = null,
    @SerialName("egg")
    val egg: String,
    @SerialName("height")
    val height: String,
    @SerialName("id")
    val id: Int,
    @SerialName("img")
    val img: String,
    @SerialName("multipliers")
    val multipliers: List<Double>? = null,
    @SerialName("name")
    val name: String,
    @SerialName("next_evolution")
    val nextEvolution: List<NextEvolution>? = null,
    @SerialName("num")
    val num: String,
    @SerialName("prev_evolution")
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
    val weight: String
)

fun PokemonDto.toPokemon(): Pokemon = Pokemon(
    avgSpawns = this.avgSpawns,
    candy = this.candy,
    candyCount = this.candyCount ?: 0,
    egg = if (this.egg == "Not in Eggs" || this.egg == "Omanyte Candy") 0 else this.egg.substringBefore("km").trim()
        .toInt(),
    height = this.height.substringBefore("m").trim().toDouble(),
    id = this.id,
    img = this.img,
    multipliers = this.multipliers,
    name = this.name,
    nextEvolution = this.nextEvolution,
    num = this.num,
    prevEvolution = this.prevEvolution,
    spawnChance = this.spawnChance,
    spawnTime = this.spawnTime,
    type = this.type,
    weaknesses = this.weaknesses,
    weight = this.weight.substringBefore("kg").trim().toDouble()
)