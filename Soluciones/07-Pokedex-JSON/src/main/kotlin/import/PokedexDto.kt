package import


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexDto(
    @SerialName("pokemon")
    val pokemonDto: List<PokemonDto>
)