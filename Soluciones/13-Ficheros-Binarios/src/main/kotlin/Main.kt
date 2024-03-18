package dev.joseluisgs

import java.nio.file.Files
import java.nio.file.Path

fun main() {
    Files.createDirectories(Path.of("data"))

    val file = Path.of("data", "hello.txt").toFile()
    file.writeText("Hello, World!")
    println(file.readText())

    // Ahora en binario
    val fileBin = Path.of("data", "hello.dat").toFile()
    fileBin.writeBytes("Hello, World!".toByteArray())
    println(fileBin.readBytes().toString(Charsets.UTF_8))
}