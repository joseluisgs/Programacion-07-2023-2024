package dev.joseluisgs.mappers

import dev.joseluisgs.dto.VehiculoDto
import dev.joseluisgs.models.Vehiculo
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun Vehiculo.toDto(): VehiculoDto {
    return VehiculoDto(
        matricula = this.matricula,
        marca = this.marca,
        modelo = this.modelo,
        color = this.color,
        precio = this.precio.toLocalMoney(),
        fechaMatriculacion = this.fechaMatriculacion.toString(),
        vendido = if (this.vendido) "Si" else "No",
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )
}

fun VehiculoDto.toModel(): Vehiculo {
    return Vehiculo(
        matricula = this.matricula,
        marca = this.marca,
        modelo = this.modelo,
        color = this.color,
        precio = this.precio.toDouble(),
        fechaMatriculacion = LocalDate.parse(this.fechaMatriculacion),
        vendido = this.vendido.toBooleanStrict(),
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt)
    )
}

fun Double.toLocalMoney(): String {
    return NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(this)
}