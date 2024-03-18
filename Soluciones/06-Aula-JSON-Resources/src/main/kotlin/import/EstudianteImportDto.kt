package import


import dev.joseluisgs.models.Estudiante
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EstudianteImportDto(
    @SerialName("edad")
    val edad: String,
    @SerialName("nombre")
    val nombre: String
)

fun EstudianteImportDto.toEstudiante(): Estudiante {
    return Estudiante(this.nombre, this.edad.toInt())
}