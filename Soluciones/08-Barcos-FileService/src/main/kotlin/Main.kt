package dev.joseluisgs

import dev.joseluisgs.dev.joseluisgs.validator.barco.BarcosValidator
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.repositories.barcos.BarcosRepositoryImpl
import dev.joseluisgs.repositories.cache.CacheImpl
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
        validator = BarcosValidator()
    )

    service.getAll().forEach { println(it) }

}