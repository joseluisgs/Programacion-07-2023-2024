package dev.joseluisgs.repositories.crud

interface CrudRepository<T, ID> {
    fun getAll(): List<T>
    fun getById(id: ID): T?
    fun save(value: T): T
    fun update(id: ID, value: T): T?
    fun delete(id: ID): T?
}