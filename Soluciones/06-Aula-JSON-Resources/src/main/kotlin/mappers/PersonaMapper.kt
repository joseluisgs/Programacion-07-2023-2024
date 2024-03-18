package dev.joseluisgs.mappers

import dev.joseluisgs.dto.DocenteDto
import dev.joseluisgs.dto.EstudianteDto
import dev.joseluisgs.dto.PersonasDto
import dev.joseluisgs.models.Docente
import dev.joseluisgs.models.Estudiante
import dev.joseluisgs.models.Persona

fun PersonasDto.toPersona(): Persona {
    return when (this.tipo) {
        "Alumno" -> Estudiante(this.nombre, this.edad?.toInt() ?: 0)
        "Profesor" -> Docente(this.nombre, this.modulo ?: "")
        else -> throw IllegalArgumentException("Tipo de persona no soportado")
    }
}

fun Docente.toDocenteDto(): DocenteDto {
    return DocenteDto(this.nombre, this.modulo)
}

fun DocenteDto.toDocente(): Docente {
    return Docente(this.nombre, this.modulo)
}

fun Estudiante.toEstudianteDto(): EstudianteDto {
    return EstudianteDto(this.nombre, this.edad.toString())
}

fun EstudianteDto.toEstudiante(): Estudiante {
    return Estudiante(this.nombre, this.edad.toInt())
}
