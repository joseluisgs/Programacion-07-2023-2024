package dev.joseluisgs.repositories.cache

import org.lighthousegames.logging.logging

private val logger = logging()

class CacheImpl<T, KEY>(
    val size: Int = 5
) : Cache<T, KEY> {

    private val cache = mutableMapOf<KEY, T>()

    override fun get(key: KEY): T? {
        logger.info { "Obteniendo valor de la cache por clave: $key" }
        return cache[key]
    }

    override fun put(key: KEY, value: T) {
        logger.info { "Guardando valor en la cache por clave $key" }
        if (cache.size >= size && !cache.containsKey(key)) {
            val firstKey = cache.keys.first()
            logger.info { "Eliminando valor de la cache por clave $firstKey" }
            cache.remove(firstKey)
        }
        cache[key] = value

    }

    override fun remove(key: KEY) {
        logger.info { "Eliminando valor de la cache por clave $key" }
        cache.remove(key)
    }

    override fun clear() {
        logger.info { "Limpiando la cache" }
        cache.clear()
    }

}