package dev.joseluisgs.exceptions.barco

sealed class BarcoExceptions(message: String) : Exception(message) {
    class TypeNotFound(message: String) : BarcoExceptions("El tipo de barco: $message no es válido")
    class BarcoNotValid(message: String) : BarcoExceptions("El barco no es válido: $message")
    class BarcoNotFound(message: String) : BarcoExceptions("No se encontró el barco: $message")
    class BarcoAlreadyExists(message: String) : BarcoExceptions("El barco ya existe: $message")
}