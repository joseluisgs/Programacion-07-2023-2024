package dev.joseluisgs.repositories.cache

import dev.joseluisgs.config.Config
import org.lighthousegames.logging.logging

private val logger = logging()

class CacheImpl<T, KEY>(
    val size: Int = Config.cacheSize
) : Cache<T, KEY> {

    private val cache = mutableMapOf<KEY, T>()

    override fun get(key: KEY): T? {
        logger.debug { "Obteniendo valor de la cache por clave: $key" }
        return cache[key]
    }

    override fun put(key: KEY, value: T) {
        logger.debug { "Guardando valor en la cache por clave $key" }
        if (cache.size >= size && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            logger.debug { "Eliminando valor de la cache por clave $firstKey" }
            cache.remove(firstKey)
        }
        cache[key] = value

    }

    override fun remove(key: KEY) {
        logger.debug { "Eliminando valor de la cache por clave $key" }
        cache.remove(key)
    }

    override fun clear() {
        logger.debug { "Limpiando la cache" }
        cache.clear()
    }

}