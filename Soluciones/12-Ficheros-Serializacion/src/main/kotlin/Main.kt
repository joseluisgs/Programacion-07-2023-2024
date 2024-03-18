package dev.joseluisgs

import dev.joseluisgs.models.Cliente
import dev.joseluisgs.models.Producto
import dev.joseluisgs.models.Venta
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Path

fun main() {
    Files.createDirectories(Path.of("data"))

    // Crear un cliente
    val cliente = Cliente("Pepe", "pepe@pepe.com")
    val venta = Venta(1, "2021-01-01", cliente)
    venta.addLinea(Producto("Laptop", 1000.0), 1)
    venta.addLinea(Producto("Ratón", 20.0), 2)

    println(venta)

    val fileWriteSerialized = File("data/venta.dat")
    val oos = ObjectOutputStream(fileWriteSerialized.outputStream())
    oos.use {
        it.writeObject(venta)
    }

    val fileReadSerialized = File("data/venta.dat")
    val ois = ObjectInputStream(fileReadSerialized.inputStream())
    ois.use {
        val venta = it.readObject() as Venta
        println(venta)
    }

}