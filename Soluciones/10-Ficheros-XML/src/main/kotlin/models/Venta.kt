package dev.joseluisgs.models

import kotlinx.serialization.Serializable

@Serializable
data class Venta(
    val id: Int,
    val fecha: String,
    val cliente: Cliente,
) {
    private val lineas = mutableListOf<LineaVenta>()
    val total
        get() = lineas.sumOf { it.precio * it.cantidad }

    fun addLinea(producto: Producto, cantidad: Int) {
        lineas.add(LineaVenta(producto, cantidad, producto.precio))
    }

    fun removeLinea(producto: Producto) {
        lineas.removeIf { it.producto == producto }
    }

    override fun toString(): String {
        return "Venta(id=$id, fecha='$fecha', cliente=$cliente, lineas=$lineas, total=$total)"
    }


    @Serializable
    data class LineaVenta(
        val producto: Producto,
        val cantidad: Int,
        val precio: Double
    )
}