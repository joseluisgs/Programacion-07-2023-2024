package dev.joseluisgs

import dev.joseluisgs.dto.VehiculoDto
import dev.joseluisgs.mappers.toModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.io.path.*

private val json = Json { prettyPrint = true }

fun main() {
    val actualDir = System.getProperty("user.dir")
    val dataFile = Path(actualDir, "data", "vehiculos.csv")

    // Existe y es un archivo
    if (dataFile.exists() && !dataFile.isDirectory() && dataFile.extension == "csv" && dataFile.isReadable()) {
        println("El archivo de la base de datos existe")
    } else {
        println("El archivo de la base de datos no existe")
    }

    // Lo leemos

    val vehiculos = dataFile.readLines()
        .drop(1)// Quitamos la cabecera
        .map { line -> line.split(",") }
        .map { parts ->
            VehiculoDto(
                matricula = parts[0],
                marca = parts[1],
                modelo = parts[2],
                color = parts[3],
                precio = parts[4],
                fechaMatriculacion = parts[5],
                vendido = parts[6],
                createdAt = parts[7],
                updatedAt = parts[8],
            )
        }

    vehiculos.forEach { println(it) }

    // Creamos el JSON
    // val jsonString = Json.encodeToString<List<VehiculoDto>>(vehiculos)
    // prettyPrint
    val jsonString = Json { prettyPrint = true }.encodeToString<List<VehiculoDto>>(vehiculos)
    println(jsonString)

    // Salvamos en un archivo
    val jsonFile = Path(actualDir, "data", "vehiculos.json")
    jsonFile.writeText(jsonString)

    // Leemos el JSON
    val jsonVehiculos = jsonFile.readText()
    println(jsonVehiculos)
    val jsonVehiculosDto = Json.decodeFromString<List<VehiculoDto>>(jsonVehiculos)
    val listaVehiculos = jsonVehiculosDto.map { it.toModel() }
    listaVehiculos.forEach { println(it) }
}

