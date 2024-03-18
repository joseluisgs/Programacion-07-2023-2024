package dev.joseluisgs.services

import dev.joseluisgs.models.Alumno

interface Storage {
    fun save(list: List<Alumno>)
    fun load(): List<Alumno>
}