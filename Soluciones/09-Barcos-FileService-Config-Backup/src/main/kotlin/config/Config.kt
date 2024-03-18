package dev.joseluisgs.config

import org.lighthousegames.logging.logging
import java.util.*

private val logger = logging()

object Config {
    var storageDir: String = "data"
        private set
    var storageFile: String = "barcos.json"
        private set
    var cacheSize: Int = 5
        private set
    var storageBackupDir: String = "backup"
        private set
    var storageBackupFile: String = "barcos.zip"
        private set

    init {
        val properties = Properties()
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
        logger.debug { "Cargando configuración" }
        storageDir = properties.getOrDefault("storage.dir", storageDir).toString()
        storageFile = properties.getProperty("storage.file", storageFile)
        cacheSize = properties.getProperty("cache.size", cacheSize.toString()).toInt()
        storageBackupDir = properties.getProperty("storage.backup.dir", storageBackupDir)
        storageBackupFile = properties.getProperty("storage.backup.file", storageBackupFile)
    }
}