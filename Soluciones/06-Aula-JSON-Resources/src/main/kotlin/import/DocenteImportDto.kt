package import


import dev.joseluisgs.models.Docente
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DocenteImportDto(
    @SerialName("modulo")
    val modulo: String,
    @SerialName("nombre")
    val nombre: String
)

fun DocenteImportDto.toDocente(): Docente {
    return Docente(this.modulo, this.nombre)
}