package dev.joseluisgs

import dev.joseluisgs.factories.AlumnadoFactory
import dev.joseluisgs.services.*

fun main() {
    println("Hola Ficheros")

    println("Alumnos como texto...")
    val storageTexto = StorageTexto()
    storageTexto.save(AlumnadoFactory.alumnado)
    storageTexto.load().forEach { println(it) }

    println()
    println("Alumnos como CSV...")
    val storageCsv = StorageCsv()
    storageCsv.save(AlumnadoFactory.alumnado)
    storageCsv.load().forEach { println(it) }

    println()
    println("Alumnos como JSON...")
    val storageJson = StorageJson()
    storageJson.save(AlumnadoFactory.alumnado)
    storageJson.load().forEach { println(it) }

    println()
    println("Alumnos como XML...")
    val storageXml = StorageXml()
    storageXml.save(AlumnadoFactory.alumnado)
    storageXml.load().forEach { println(it) }

    println()
    println("Alumnos como Binario Serializable...")
    val storageSerializable = StorageSerializable()
    storageSerializable.save(AlumnadoFactory.alumnado)
    storageSerializable.load().forEach { println(it) }

    println()
    println("Alumnos como Binario...")
    val storageBinario = StorageBinario()
    storageBinario.save(AlumnadoFactory.alumnado)
    storageBinario.load().forEach { println(it) }

}