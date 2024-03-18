package dev.joseluisgs.services.storage.base

import java.io.File

interface FileStorage<T> {
    fun readFromFile(file: File): List<T>
    fun writeToFile(list: List<T>, file: File)
}