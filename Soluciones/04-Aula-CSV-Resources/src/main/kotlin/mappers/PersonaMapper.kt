package dev.joseluisgs.mappers

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