package import


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrevEvolution(
    @SerialName("name")
    val name: String,
    @SerialName("num")
    val num: String
)