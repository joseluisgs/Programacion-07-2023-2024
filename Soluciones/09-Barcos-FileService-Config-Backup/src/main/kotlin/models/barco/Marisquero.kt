package dev.joseluisgs.models.barco

import java.time.LocalDate

class Marisquero(
    matricula: String,
    nombre: String,
    patron: String,
    cargaActual: Double,
    fechaIncorporacion: LocalDate,
    val experienciaTripulacion: Int
) : Barco(matricula, nombre, patron, cargaActual, fechaIncorporacion), PescarConCaña, PescarConRed {

    override fun pescarConCaña() {
        println("Marisquero pescando con caña")
    }

    override fun pescarConRed() {
        println("Marisquero pescando con red")
    }

    override fun toString(): String {
        return "Marisquero(matricula='$matricula', nombre='$nombre', patron='$patron', cargaActual=$cargaActual, fechaIncorporacion=$fechaIncorporacion, experienciaTripulacion=$experienciaTripulacion)"
    }
}