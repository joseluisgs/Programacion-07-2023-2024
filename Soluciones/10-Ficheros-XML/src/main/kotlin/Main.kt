package dev.joseluisgs

import dev.joseluisgs.models.Cliente
import dev.joseluisgs.models.Producto
import dev.joseluisgs.models.Venta
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File
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

    val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    val fileWriteJson = File("data/venta.json")
    fileWriteJson.writeText(json.encodeToString(venta))

    val fileReadJson = File("data/venta.json")
    val ventaFromJson = json.decodeFromString<Venta>(fileReadJson.readText())
    println(ventaFromJson)

    val xml = XML { indent = 4 }
    val fileWriteXml = File("data/venta.xml")
    fileWriteXml.writeText(xml.encodeToString(venta))
    val fileReadXml = File("data/venta.xml")
    val ventaFromXml = xml.decodeFromString<Venta>(fileReadXml.readText())
    println(ventaFromXml)
    
}