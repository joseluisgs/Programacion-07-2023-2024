package dev.joseluisgs.dto

import kotlinx.serialization.Serializable

@Serializable
data class InformeDto(
    val docentes: List<DocenteDto>,
    val estudiantes: List<EstudianteDto>
)
