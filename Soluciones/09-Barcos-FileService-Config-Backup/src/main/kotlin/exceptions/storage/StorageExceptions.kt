package dev.joseluisgs.exceptions.storage

sealed class StorageExceptions(message: String) : Exception(message) {
    class FileNotFound(message: String) : StorageExceptions(message)
}