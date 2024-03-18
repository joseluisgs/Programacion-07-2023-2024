package dev.joseluisgs

import java.io.File

fun main() {
    // Crear un fichero en el directorio actual
    val file = File("file.txt")

    // existe
    if (file.exists()) {
        println("El fichero existe")
    } else {
        println("El fichero no existe")
    }

    // Escribir en el fichero (Si no existe lo crea)
    // Si existe lo sobreescribe completo OJO!!!
    file.writeText("Hola Mundo")

    // Existe, muy importante para no usar ficheros que no existen (excepciones y ahorra try-catch)
    if (file.exists()) {
        println("El fichero existe")
    } else {
        println("El fichero no existe")
    }

    // Leer el fichero completo, ojo con el tamaño
    val contenido = file.readText()
    println(contenido)

    // Leer el fichero por líneas, a nivel completo ojo con el tamaño
    val lineas = file.readLines()
    lineas.forEach { println(it) }

    // Leer el fichero por líneas e ir procesando línea a línea
    file.forEachLine { println(it) }

    // Añadir contenido al fichero
    file.appendText("\nAdios Mundo")

    // Leer el fichero completo, ojo con el tamaño
    val contenido2 = file.readText()
    println(contenido2)

    // Borrar el fichero
    file.delete()

    // file.forEachLine { println(it) }

    // Vamos a leer el fichero build.gradle.kts
    val buildFile = File("build.gradle.kts")
    if (buildFile.exists()) {
        var version = buildFile
            .readLines()// Leemos todas las líneas de un fichero como lista de Strings
            .first { it.contains(" kotlin(\"jvm\")") }
            .split("version").last()
        // imprimir la versión de Kotlin
        println("La versión de Kotlin es: ${version.replace("\"", "").trim()}")

        // Otra forma de leer el fichero, es una a una, ideal si fuera muy grande, porque no leemos todo el fichero
        buildFile.forEachLine {
            if (it.contains(" kotlin(\"jvm\")")) {
                version = it.split("version").last()
                println("La versión de Kotlin es: ${version.replace("\"", "").trim()}")
            }
        }

    } else {
        println("El fichero no existe")
    }

}