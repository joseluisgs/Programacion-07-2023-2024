package dev.joseluisgs.services.barcos

import dev.joseluisgs.models.barco.Barco
import java.io.File

interface BarcosService {
    fun getAll(page: Int? = null, size: Int? = null): List<Barco>
    fun getByName(name: String): List<Barco>
    fun getByPatron(patron: String): List<Barco>
    fun getByMatricula(matricula: String): Barco
    fun save(barco: Barco): Barco
    fun update(barco: Barco): Barco
    fun delete(matricula: String): Barco
    fun exportToFile(file: File)
    fun importFromFile(file: File)
    fun backup()
    fun restore()
}