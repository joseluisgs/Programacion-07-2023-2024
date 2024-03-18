package dev.joseluisgs

import dev.joseluisgs.models.Cliente
import dev.joseluisgs.models.Producto
import dev.joseluisgs.models.Venta
import java.io.*
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

    val fileWriteBytes = File("data/venta.dat")
    ByteArrayOutputStream().use { bos ->
        ObjectOutputStream(bos).use { oos ->
            oos.writeObject(venta)
            fileWriteBytes.writeBytes(bos.toByteArray())
        }
    }

    val fileReadBytes = File("data/venta.dat")
    fileReadBytes.readBytes().let { bytes ->
        ObjectInputStream(ByteArrayInputStream(bytes)).use { ois ->
            val venta = ois.readObject() as Venta
            println(venta)
        }
    }

}