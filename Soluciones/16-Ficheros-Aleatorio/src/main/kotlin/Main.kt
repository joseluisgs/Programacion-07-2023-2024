package dev.joseluisgs

import java.io.RandomAccessFile
import kotlin.io.path.Path

fun main() {
    //leerFicheroAleatorioTexto()
    mostrarFicheroAleatorio()
    modificarFicheroAleatorio()
    mostrarFicheroAleatorio()
    //palabras()
}

fun palabras() {
    val fileOrigen =
        Path(
            System.getProperty("user.dir"),
            "data",
            "texto.txt"
        ).toFile()///"$programPath${File.separator}data${File.separator}texto.txt"
    val aleatorio = RandomAccessFile(fileOrigen, "rw")

    println("Introduce una palabra: ")
    val palabra = readlnOrNull() ?: throw Exception("Palabra no válida")
    println(palabra)
    aleatorio.use {
        var cadena = aleatorio.readLine()
        var posicion = aleatorio.filePointer
        while (cadena != null) {
            if (cadena.contains(palabra)) {
                // println(cadena)

                val indice = cadena.indexOf(palabra)
                val auxBuilder = StringBuilder(cadena)
                auxBuilder.replace(indice, indice + palabra.length, palabra.uppercase())
                cadena = auxBuilder.toString()

                aleatorio.seek(posicion)
                aleatorio.write(cadena.toByteArray())

            }
            posicion = aleatorio.filePointer
            cadena = aleatorio.readLine()
        }
    }
}

fun modificarFicheroAleatorio() {

    val fileOrigen = Path(System.getProperty("user.dir"), "data", "enteros.dat").toFile()
    val aleatorio = RandomAccessFile(fileOrigen, "rw")
    // use!!!!!!!!
    aleatorio.use {
        it.seek(0)
        val longitud = it.length()
        val numEnteros = longitud / 4
        println("Numero de entradas: $numEnteros")
        // indicar el entero a modificar
        println("El entero a modificar [1-$numEnteros]: ")
        val entero = readlnOrNull()?.toIntOrNull() ?: 0
        if (entero !in 1..numEnteros) {
            println("El entero no es correcto")
            return
        }
        // posicionarse en el byte a modificar
        it.seek((entero - 1) * 4L)
        // leer el entero
        val valor = it.readInt()
        println("El valor del entero es: $valor")
        // pedir el nuevo valor
        println("Nuevo valor: ")
        val nuevoValor = readlnOrNull()?.toIntOrNull() ?: 0
        // modificar el entero
        it.seek((entero - 1) * 4L)
        it.writeInt(nuevoValor)
    }
}

fun mostrarFicheroAleatorio() {
    val fileOrigen = Path(System.getProperty("user.dir"), "data", "enteros.dat")
    val aleatorio = RandomAccessFile(fileOrigen.toFile(), "r")
    // posicionarse en el byte 0
    aleatorio.use {
        aleatorio.seek(0)
        val longitud = aleatorio.length()
        println("Longitud del fichero: $longitud")
        // leemos todos los enteros
        var entero: Int
        val numEnteros = longitud / 4
        repeat(numEnteros.toInt()) {
            entero = aleatorio.readInt()
            println("Entero: $entero - Posición: ${aleatorio.filePointer}")
        }
    }
}

fun leerFicheroAleatorioTexto() {
    val fileOrigen = Path(System.getProperty("user.dir"), "data", "texto.txt").toFile()
    val aleatorio = RandomAccessFile(fileOrigen, "r")
    var linea: String?
    aleatorio.use {
        //aleatorio.seek(0)
        do {
            linea = aleatorio.readLine()
            if (linea != null) {
                println(linea)
            }
        } while (linea != null)
    }
}
