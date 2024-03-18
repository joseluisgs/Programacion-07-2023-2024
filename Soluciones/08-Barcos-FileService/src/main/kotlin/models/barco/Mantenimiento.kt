package dev.joseluisgs.models.barco

import java.time.LocalDate

class Mantenimiento(
    matricula: String,
    nombre: String,
    patron: String,
    cargaActual: Double,
    fechaIncorporacion: LocalDate,
    val activo: Boolean
) : Barco(matricula, nombre, patron, cargaActual, fechaIncorporacion) {

    fun reparar(barco: Barco) {
        println("Mantenimeinto reparando un barco")
    }

    override fun toString(): String {
        return "Mantenimiento(matricula='$matricula', nombre='$nombre', patron='$patron', cargaActual=$cargaActual, fechaIncorporacion=$fechaIncorporacion, activo=$activo)"
    }
}