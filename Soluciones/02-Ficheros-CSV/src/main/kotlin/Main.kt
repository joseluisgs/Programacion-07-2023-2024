package dev.joseluisgs

import dev.joseluisgs.models.Vehiculo
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.io.path.*

fun main() {
    val actualDir = System.getProperty("user.dir")
    println("El directorio actual es: $actualDir")

    val databaseDir = Path(actualDir, "data")

    // Existe y es un directorio
    if (databaseDir.exists() && databaseDir.isDirectory()) {
        println("El directorio de la base de datos existe")
    } else {
        println("El directorio de la base de datos no existe")
    }

    val dataFile = Path(databaseDir.toString(), "vehiculos.csv")

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
            Vehiculo(
                matricula = parts[0],
                marca = parts[1],
                modelo = parts[2],
                color = parts[3],
                precio = parts[4].toDouble(),
                fechaMatriculacion = LocalDate.parse(parts[5]),
                vendido = parts[6].toBoolean(),
                createdAt = LocalDateTime.parse(parts[7]),
                updatedAt = LocalDateTime.parse(parts[8])
            )
        }

    vehiculos.forEach { println(it) }

    // Escribimos un fichero csv con los datos de los vehiculos
    val vehiculosFile = Path(databaseDir.toString(), "vehiculos-back.csv")
    vehiculosFile.writeText("matricula,marca,modelo,color,precio,fechaMatriculacion,vendido,createdAt,updatedAt\n")
    vehiculos.forEach {
        vehiculosFile.appendText(
            "${it.matricula},${it.marca},${it.modelo},${it.color},${it.precio},${it.fechaMatriculacion},${it.vendido},${it.createdAt},${it.updatedAt}\n",
        )
    }

    // Comprobamos que se ha escrito bien

}

