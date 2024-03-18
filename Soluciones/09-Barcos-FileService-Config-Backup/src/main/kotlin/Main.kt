package dev.joseluisgs

import dev.joseluisgs.dev.joseluisgs.validator.barco.BarcosValidator
import dev.joseluisgs.models.barco.*
import dev.joseluisgs.repositories.barcos.BarcosRepositoryImpl
import dev.joseluisgs.repositories.cache.CacheImpl
import dev.joseluisgs.services.backup.BackupImpl
import dev.joseluisgs.services.barcos.BarcosService
import dev.joseluisgs.services.barcos.BarcosServiceImpl
import dev.joseluisgs.services.storage.barco.BarcosFileStorageJson
import dev.joseluisgs.services.storage.barcos.BarcosFileStorageCsv

fun main() {

    val service: BarcosService = BarcosServiceImpl(
        repository = BarcosRepositoryImpl(
            fileStorage = BarcosFileStorageJson()
        ),
        storage = BarcosFileStorageCsv(),
        cache = CacheImpl<Barco, String>(),
        validator = BarcosValidator(),
        backup = BackupImpl()
    )

    // service.getAll().forEach { println(it) }

    service.backup()
    service.restore()

    val barcos = service.getAll()

    //Todos los barcos ordenados por fecha de matriculación ascendente.
    println("Todos los barcos ordenados por fecha de matriculación ascendente.")
    barcos.sortedBy { it.fechaIncorporacion }.forEach { println(it) }

    println()
    //Todos los barcos atuneros
    println("Todos los barcos atuneros")
    barcos.filterIsInstance<Atunero>().forEach { println(it) }

    println()
    //Carga actual máxima de los marisqueros.
    println("Carga actual máxima de los marisqueros.")
    barcos.filterIsInstance<Marisquero>().maxOf { it.cargaActual }.let { println(it) }

    println()
    //Media de experiencia en los marisqueros.
    println("Media de experiencia en los marisqueros.")
    barcos.filterIsInstance<Marisquero>().map { it.experienciaTripulacion }.average().let { println(it) }

    println()
    //Números de barcos ordenados por tipo.
    println("Números de barcos ordenados por tipo.")
    barcos.groupBy { it::class.simpleName }.forEach { println("${it.key} -> ${it.value.size}") }

    println()
    //Barcos con capacidad de pescar con caña agrupados por patrón.
    println("Barcos con capacidad de pescar con caña agrupados por patrón.")
    barcos.filter { it is PescarConCaña }.groupBy { it.patron }
        .forEach { println("${it.key} -> ${it.value.size}") }

    println()
    //Barcos agrupados por capacidad de pescar o sin capacidad de pescar.
    println("Barcos agrupados por capacidad de pescar o sin capacidad de pescar.")
    barcos.groupBy { if (it is PescarConCaña || it is PescarConRed) "Barcos Pescadores" else "No Pescadores" }
        .forEach { println("${it.key} -> ${it.value}") }

    println()
    // Barco cuyo patrón sea el que tiene la longitud más larga.
    println("Barco cuyo patrón sea el que tiene la longitud más larga.")
    barcos.maxByOrNull { it.patron.length }.let { println(it) }

    println()
    //Barcos reparadores en grupos de si están activos o no.
    println("Barcos reparadores en grupos de si están activos o no.")
    barcos.filterIsInstance<Mantenimiento>().groupBy { if (it.activo) "Activos" else "Inactivos" }
        .forEach { println("${it.key} -> ${it.value}") }

    println()
    //Barco atunero con carga máxima.
    println("Barco atunero con carga máxima.")
    barcos.filterIsInstance<Atunero>().maxByOrNull { it.cargaActual }.let { println(it) }

    println()
    // Barco más moderno.
    println("Barco más moderno.")
    barcos.maxByOrNull { it.fechaIncorporacion }.let { println(it) }
}
