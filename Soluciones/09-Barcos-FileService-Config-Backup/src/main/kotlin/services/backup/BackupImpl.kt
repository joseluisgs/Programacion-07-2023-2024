package dev.joseluisgs.services.backup

import dev.joseluisgs.config.Config
import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.Path
import kotlin.io.path.createTempDirectory


private val logger = logging()

class BackupImpl : Backup {
    override fun backup() {
        // No hace falta pasar por el temporal, pero lo hacemos para practicar
        logger.debug { "BackupImpl: Realizando backup" }
        // Creamos un directorio temporal
        val tempDir = createTempDirectory("barcos_backup")
        logger.debug { "BackupImpl: Directorio temporal: $tempDir" }
        val filePath = Path(Config.storageDir, Config.storageFile)
        // Copiamos el fichero a nuestro directorio temporal
        Files.copy(filePath, tempDir.resolve(Config.storageFile), StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "BackupImpl: Fichero copiado: $filePath" }
        // Creamos un zip con el fichero
        val zipFile = tempDir.resolve(Config.storageBackupFile)
        ZipOutputStream(Files.newOutputStream(zipFile)).use { zip ->
            zip.putNextEntry(ZipEntry(Config.storageFile))
            Files.copy(tempDir.resolve(Config.storageFile), zip)
            zip.closeEntry()
        }
        logger.debug { "BackupImpl: Fichero comprimido: $zipFile" }

        // Copiamos el zip a un directorio de backup
        if (!Files.exists(Path(Config.storageBackupDir))) {
            Files.createDirectories(Path(Config.storageBackupDir))
        }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        Files.copy(zipFile, backupDir, StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "BackupImpl: Fichero copiado a directorio de backup: $backupDir" }

        // Borramos el fichero original
        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
        logger.debug { "BackupImpl: Directorio temporal borrado: $tempDir" }
    }

    override fun restore() {
        logger.debug { "BackupImpl: Realizando restore" }
        val backupDir = Path(Config.storageBackupDir, Config.storageBackupFile)
        val tempDir = createTempDirectory("barcos_restore")
        logger.debug { "BackupImpl: Directorio temporal: $tempDir" }
        // Copiamos el zip a nuestro directorio temporal
        Files.copy(backupDir, tempDir.resolve(Config.storageBackupFile), StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "BackupImpl: Fichero copiado: $backupDir" }
        // Descomprimimos el fichero
        val zipFile = tempDir.resolve(Config.storageBackupFile)
        if (!Files.exists(zipFile)) {
            logger.error { "BackupImpl: Fichero de backup no encontrado" }
            return
        }
        if (Files.size(zipFile) == 0L) {
            logger.error { "BackupImpl: Fichero de backup vacío" }
            return
        }
        
        ZipFile(zipFile.toFile()).use { zip ->
            if (zip.entries().asSequence().none()) {
                logger.error { "BackupImpl: Fichero de backup vacío" }
                return
            }
            zip.entries().asSequence().forEach { archivos ->
                zip.getInputStream(archivos).use { input ->
                    Files.copy(input, tempDir.resolve(archivos.name), StandardCopyOption.REPLACE_EXISTING)
                    logger.debug { "BackupImpl: Fichero descomprimido: $archivos" }
                }
            }
        }
        // Copiamos el fichero a su ubicación original
        val filePath = Path(Config.storageDir, Config.storageFile)
        Files.copy(tempDir.resolve(Config.storageFile), filePath, StandardCopyOption.REPLACE_EXISTING)
        logger.debug { "BackupImpl: Fichero restaurado: $filePath" }
        // Borramos el fichero temporal
        tempDir.toFile().deleteRecursively()
        tempDir.toFile().deleteOnExit()
        logger.debug { "BackupImpl: Directorio temporal borrado: $tempDir" }
    }
}