package dev.joseluisgs.repositories.cache

interface Cache<T, KEY> {
    fun get(key: KEY): T?
    fun put(key: KEY, value: T)
    fun remove(key: KEY)
    fun clear()
}