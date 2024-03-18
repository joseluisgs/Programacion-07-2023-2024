package import


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImportDto(
    @SerialName("docentes")
    val docentes: List<DocenteImportDto>,
    @SerialName("estudiantes")
    val estudiantes: List<EstudianteImportDto>
)