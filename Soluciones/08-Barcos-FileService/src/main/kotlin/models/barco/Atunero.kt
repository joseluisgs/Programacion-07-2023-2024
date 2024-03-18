package dev.joseluisgs.models.barco

import java.time.LocalDate

class Atunero(
    matricula: String,
    nombre: String,
    patron: String,
    cargaActual: Double,
    fechaIncorporacion: LocalDate,
    val cargaMaxima: Double
) : Barco(matricula, nombre, patron, cargaActual, fechaIncorporacion), PescarConCaña {

    override fun pescarConCaña() {
        println("Atunero pescando con caña")
    }

    override fun toString(): String {
        return "Atunero(matricula='$matricula', nombre='$nombre', patron='$patron', cargaActual=$cargaActual, fechaIncorporacion=$fechaIncorporacion, cargaMaxima=$cargaMaxima)"
    }
}