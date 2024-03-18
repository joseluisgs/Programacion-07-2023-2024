package dev.joseluisgs.services.barcos

import dev.joseluisgs.dev.joseluisgs.validator.barco.BarcosValidator
import dev.joseluisgs.exceptions.barco.BarcoExceptions
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.repositories.barcos.BarcosRepository
import dev.joseluisgs.repositories.cache.Cache
import dev.joseluisgs.services.storage.base.FileStorage
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()

class BarcosServiceImpl(
    private val repository: BarcosRepository,
    private val storage: FileStorage<Barco>,
    private val cache: Cache<Barco, String>,
    private val validator: BarcosValidator
) : BarcosService {
    override fun getAll(page: Int?, size: Int?): List<Barco> {
        logger.info { "Obteniendo todos los barcos" }
        val res = repository.getAll()
        return if (page != null && size != null) {
            res.windowed(size, size, true)[page]
        } else {
            res
        }
    }

    override fun getByName(name: String): List<Barco> {
        logger.info { "Buscando barcos por nombre: $name" }
        return repository.getByName(name)
    }

    override fun getByPatron(patron: String): List<Barco> {
        logger.info { "Buscando barcos por patrón: $patron" }
        return repository.getByPatron(patron)
    }

    private fun getBarcoById(id: String): Barco? {
        cache.get(id)?.let {
            logger.info { "Barco encontrado en cache: $it" }
            return it
        }
        return repository.getById(id)?.let {
            logger.info { "Barco encontrado en repositorio: $it" }
            cache.put(it.matricula, it)
            return it
        }
    }

    override fun getByMatricula(matricula: String): Barco {
        logger.info { "Buscando barco por matrícula: $matricula" }
        return getBarcoById(matricula)
            ?: throw BarcoExceptions.BarcoNotFound(matricula)
    }

    override fun save(barco: Barco): Barco {
        logger.info { "Guardando barco: $barco" }
        validator.validate(barco)
        // La matricula no puede existir
        if (this.getBarcoById(barco.matricula) != null) {
            throw BarcoExceptions.BarcoAlreadyExists(barco.matricula)
        }
        return repository.save(barco).also {
            cache.put(it.matricula, it)
        }
    }

    override fun update(barco: Barco): Barco {
        logger.info { "Actualizando barco: $barco" }
        validator.validate(barco)
        // Existe??
        if (this.getBarcoById(barco.matricula) == null) {
            throw BarcoExceptions.BarcoNotFound(barco.matricula)
        }
        repository.update(barco.matricula, barco).also {
            cache.put(it!!.matricula, it)
            return it
        }
    }

    override fun delete(matricula: String): Barco {
        logger.info { "Borrando barco con matrícula: $matricula" }
        if (this.getBarcoById(matricula) == null) {
            throw BarcoExceptions.BarcoNotFound(matricula)
        }
        repository.delete(matricula).also {
            cache.remove(it!!.matricula)
            return it
        }
    }

    override fun exportToFile(file: File) {
        logger.info { "Exportando barcos a CSV: $file" }
        storage.writeToFile(repository.getAll(), file)
    }

    override fun importFromFile(file: File) {
        logger.info { "Importando barcos desde CSV: $file" }
        storage.readFromFile(file).forEach {
            repository.save(it)
        }
    }
}