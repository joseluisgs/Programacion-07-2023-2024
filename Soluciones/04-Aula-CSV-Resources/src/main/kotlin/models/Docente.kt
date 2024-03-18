package dev.joseluisgs.models

class Docente(nombre: String, val modulo: String) : Persona(nombre) {
    override fun toString(): String {
        return "Docente(nombre=$nombre, modulo=$modulo)"
    }
}
