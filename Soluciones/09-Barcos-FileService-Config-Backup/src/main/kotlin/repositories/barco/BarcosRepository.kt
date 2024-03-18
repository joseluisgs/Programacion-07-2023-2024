package dev.joseluisgs.repositories.barcos

import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.repositories.crud.CrudRepository

interface BarcosRepository : CrudRepository<Barco, String> {
    fun getByName(name: String): List<Barco>
    fun getByPatron(patron: String): List<Barco>
    fun loadData()
}