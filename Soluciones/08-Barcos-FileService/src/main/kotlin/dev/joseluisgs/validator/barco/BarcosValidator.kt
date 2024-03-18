package dev.joseluisgs.dev.joseluisgs.validator.barco

import dev.joseluisgs.exceptions.barco.BarcoExceptions
import dev.joseluisgs.models.barco.Atunero
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.models.barco.Marisquero
import java.time.LocalDate

class BarcosValidator {
    fun validate(barco: Barco) {
        validateMatricula(barco.matricula, barco.javaClass.simpleName)
        validateNombre(barco.nombre)
        validatePatron(barco.patron)
        validateCargaActual(barco.cargaActual)
        validateFechaIncorporacion(barco.fechaIncorporacion)
        when (barco) {
            is Atunero -> validateCargaMaxima(barco.cargaMaxima)
            is Marisquero -> validateExperiencia(barco.experienciaTripulacion)
        }
    }

    private fun validateMatricula(matricula: String, tipo: String) {
        // Es LLL-NNNN
        val regex = Regex("^[A-Z]{3}-[0-9]{4}$")
        if (!regex.matches(matricula)) {
            throw BarcoExceptions.BarcoNotValid("La matrícula del barco $tipo no es válida")
        }
        val mat = matricula.split("-")[0]
        when (tipo) {
            "Atunero" -> {
                if (mat != "ATN") {
                    throw BarcoExceptions.BarcoNotValid("La matrícula del barco $tipo no es válida")
                }
            }

            "Marisquero" -> {
                if (mat != "MRS") {
                    throw BarcoExceptions.BarcoNotValid("La matrícula del barco $tipo no es válida")
                }
            }

            "Mantenimiento" -> {
                if (mat != "MNT") {
                    throw BarcoExceptions.BarcoNotValid("La matrícula del barco $tipo no es válida")
                }
            }
        }
    }

    private fun validateNombre(nombre: String) {
        // alfanumerico
        val regex = Regex("^[a-zA-Z0-9]+$")
        if (!regex.matches(nombre)) {
            throw BarcoExceptions.BarcoNotValid("El nombre del barco no es válido, no es alfanumérico")
        }
        if (nombre.length < 3 || nombre.length > 20) {
            throw BarcoExceptions.BarcoNotValid("El nombre del barco no es válido, debe tener entre 3 y 20 caracteres")
        }
    }

    private fun validatePatron(patron: String) {
        if (patron.length < 5 || patron.length > 30) {
            throw BarcoExceptions.BarcoNotValid("El patrón del barco no es válido, debe tener entre 5 y 30 caracteres")
        }
    }

    private fun validateCargaActual(cargaActual: Double) {
        if (cargaActual < 100 || cargaActual > 500) {
            throw BarcoExceptions.BarcoNotValid("La carga actual del barco no es válida, debe estar entre 100 y 500 toneladas")
        }
    }

    private fun validateFechaIncorporacion(fechaIncorporacion: LocalDate) {
        // entre el año 2000 y el actual
        if (fechaIncorporacion.year < 2000 || fechaIncorporacion.year > LocalDate.now().year) {
            throw BarcoExceptions.BarcoNotValid("La fecha de incorporación del barco no es válida, debe estar entre el año 2000 y el actual")
        }
    }

    private fun validateCargaMaxima(cargaMaxima: Double) {
        if (cargaMaxima < 1000 || cargaMaxima > 5000) {
            throw BarcoExceptions.BarcoNotValid("La carga máxima del barco no es válida, debe estar entre 1000 y 5000 toneladas")
        }
    }

    private fun validateExperiencia(experiencia: Int) {
        if (experiencia < 10 || experiencia > 20) {
            throw BarcoExceptions.BarcoNotValid("La experiencia de la tripulación del barco no es válida, debe estar entre 10 y 20 años")
        }
    }
}
