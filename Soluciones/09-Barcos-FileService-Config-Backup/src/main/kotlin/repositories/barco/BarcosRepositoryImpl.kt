package dev.joseluisgs.repositories.barcos

import dev.joseluisgs.config.Config
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.services.storage.base.FileStorage
import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()

class BarcosRepositoryImpl(
    private val fileStorage: FileStorage<Barco>
) : BarcosRepository {
    // Uso un mapa para simular una base de datos
    private val barcos = mutableMapOf<String, Barco>()
    private val fileImportPath = Path(Config.storageDir, Config.storageFile)

    init {
        // si no existe la carpeta data, creala
        val dataDir = Path(Config.storageDir)
        if (!dataDir.toFile().exists()) {
            dataDir.toFile().mkdir()
        }
        // Si no existe el fichero, crealo
        if (!fileImportPath.toFile().exists()) {
            fileImportPath.toFile().createNewFile()
        }
        // Cargamos los datos
        loadData()
    }

    override fun loadData() {
        logger.debug { "Cargando datos de fichero: $fileImportPath" }
        barcos.clear()
        try {
            barcos.putAll(
                fileStorage.readFromFile(fileImportPath.toFile())
                    .associateBy { it.matricula }
            )
        } catch (e: Exception) {
            logger.error { "Error al cargar los datos: ${e.message}" }
        }
    }

    private fun saveData() {
        logger.debug { "Guardando datos en fichero: $fileImportPath" }
        fileStorage.writeToFile(barcos.values.toList(), fileImportPath.toFile())
    }


    override fun getByName(name: String): List<Barco> {
        logger.debug { "Buscando barcos por nombre: $name" }
        return barcos.values.filter { it.nombre.contains(name, ignoreCase = true) }
    }

    override fun getByPatron(patron: String): List<Barco> {
        logger.debug { "Buscando barcos por patrón: $patron" }
        return barcos.values.filter { it.nombre.contains(patron, ignoreCase = true) }
    }

    override fun getAll(): List<Barco> {
        logger.debug { "Obteniendo todos los barcos" }
        return barcos.values.toList()
    }

    override fun getById(id: String): Barco? {
        logger.debug { "Obteniendo barco por id: $id" }
        return barcos[id]
    }

    override fun save(value: Barco): Barco {
        logger.debug { "Guardando barco: $value" }
        barcos[value.matricula] = value
        saveData()
        return value
    }

    override fun update(id: String, value: Barco): Barco? {
        logger.debug { "Actualizando barco con id: $id" }
        return barcos[id]?.let {
            barcos[id] = value
            saveData()
            value
        }
    }

    override fun delete(id: String): Barco? {
        logger.debug { "Borrando barco con id: $id" }
        val res = barcos.remove(id)
        saveData()
        return res
    }
}