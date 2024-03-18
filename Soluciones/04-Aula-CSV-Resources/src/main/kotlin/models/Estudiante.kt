package dev.joseluisgs.models

class Estudiante(nombre: String, val edad: Int) : Persona(nombre) {
    override fun toString(): String {
        return "Estudiante(nombre=$nombre, edad=$edad)"
    }
}