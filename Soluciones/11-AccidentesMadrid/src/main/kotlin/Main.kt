package dev.joseluisgs

import dev.joseluisgs.dev.joseluisgs.services.AccidentesService

fun main() {
    println("Accidentes de tráfico Madrid 2023")
    val accidentes = AccidentesService.accidentes

    accidentes.take(5).forEach {
        println(it)
    }

    println()
    println("Accidentes en los que estan implicados alcohol o drogas.")
    accidentes.filter { it.positivoAlcohol || it.positivoDrogas }
        .forEach {
            println(it)
        }

    println()
    println("Numero de accidentes que han dado positivo en alcohol y drogas.")
    println(accidentes.count { it.positivoAlcohol && it.positivoDrogas })

    println()
    println("Número Accidentes agrupados por sexo")
    accidentes.groupBy { it.sexo }
        .mapValues { it.value.size }
        .forEach { (sexo, cantidad) ->
            println("$sexo: $cantidad")
        }

    /*println()
    println("Accidentes agrupados por sexo")
    accidentes.groupBy { it.sexo }
        .forEach { (sexo, lista) ->
            println("$sexo: $lista")
        }*/

    println()
    println("Accidentes agrupados por meses")
    accidentes.groupBy { it.fecha.month }
        .mapValues { it.value.size }
        .forEach { (mes, cantidad) ->
            println("$mes: $cantidad")
        }

    println()
    println("Mes en el que ha habido más accidentes")
    accidentes.groupBy { it.fecha.month }
        .mapValues { it.value.size }
        .maxByOrNull { it.value }
        .let { println(it) }

    println()
    println("Accidentes agrupados por tipos de vehiculos")
    accidentes.groupBy { it.tipoVehiculo }
        .mapValues { it.value.size }
        .forEach { (vehiculo, cantidad) ->
            println("$vehiculo: $cantidad")
        }

    println()
    println("Accidentes ocurridos en la calle de Leganés")
    accidentes.filter { it.calle.contains("leganes", ignoreCase = true) }
        .forEach {
            println(it)
        }

    println()
    println("Numero de accidentes por distrito ordenados ascendentemente")
    accidentes.groupBy { it.distrito }
        .mapValues { it.value.size }
        .toList()
        .sortedBy { it.second }
        .forEach { (distrito, cantidad) ->
            println("$distrito: $cantidad")
        }

    println()
    println("Numero de accidentes en USERA")
    println(accidentes.count { it.distrito.contains("usera", ignoreCase = true) })

    println()
    println("Estadisticas por distrito (Max, Min, Avg)")
    accidentes.groupBy { it.distrito }
        .mapValues { it.value.size }
        .let {
            val max = it.maxByOrNull { it.value }
            val min = it.minByOrNull { it.value }
            val avg = it.values.average()
            println("Max: $max")
            println("Min: $min")
            println("Avg: $avg")
        }

    println()
    println("Accidentes por distrito ordenadas de manera descendente")
    accidentes.groupBy { it.distrito }
        .mapValues { it.value.size }
        .toList()
        .sortedByDescending { it.second }
        .forEach { (distrito, cantidad) ->
            println("$distrito: $cantidad")
        }

    println()
    println("Listado de accidentes que se produzcan en fin de semana y de noche (a partir de las 8 de la tarde hasta las 6 de la mañana) y que hayan dado positivo en alcohol")
    accidentes.filter { it.positivoAlcohol && it.fecha.dayOfWeek.value in 5..7 && (it.hora.hour in 20..23 || it.hora.hour in 0..6) }
        .forEach {
            println(it)
        }

    println()
    println("Accidentes agrupados por lesividad")
    accidentes.groupBy { it.lesividad }
        .mapValues { it.value.size }
        .forEach { (lesividad, cantidad) ->
            println("$lesividad: $cantidad")
        }

    println()
    println("Accidentes donde haya fallecidos")
    accidentes.filter { it.lesividad.contains("Fallecido", ignoreCase = true) }
        .forEach {
            println(it)
        }

    println()
    println("Accidentes con fallecidos y alcohol o drogas")
    accidentes.filter {
        it.lesividad.contains(
            "Fallecido",
            ignoreCase = true
        ) && (it.positivoAlcohol || it.positivoDrogas)
    }
        .forEach {
            println(it)
        }

    println()
    println("Accidentes agrupados por metorología")
    accidentes.groupBy { it.estadoMeteorologico }
        .mapValues { it.value.size }
        .forEach { (meteo, cantidad) ->
            println("$meteo: $cantidad")
        }

    println()
    println("Accidentes donde granizó agrupado por distritos")
    accidentes.filter { it.estadoMeteorologico.contains("Granizando", ignoreCase = true) }
        .groupBy { it.distrito }
        .mapValues { it.value.size }
        .forEach { (distrito, cantidad) ->
            println("$distrito: $cantidad")
        }

    println()
    println("Accidentes agrupados por Alcohol, Drogas, Alcohol y Drogas y Nada")
    accidentes.groupBy {
        when {
            it.positivoAlcohol && it.positivoDrogas -> "Alcohol y Drogas"
            it.positivoAlcohol -> "Alcohol"
            it.positivoDrogas -> "Drogas"
            else -> "Nada"
        }
    }
        .mapValues { it.value.size }
        .forEach { (tipo, cantidad) ->
            println("$tipo: $cantidad")
        }

    println()
    println("Distrito que tiene más accidentes con Alcohol")
    accidentes.filter { it.positivoAlcohol }
        .groupBy { it.distrito }
        .mapValues { it.value.size }
        .maxByOrNull { it.value }
        .let { println(it) }

    println()
    println("Distrito que tiene más accidentes con Drogas")
    accidentes.filter { it.positivoDrogas }
        .groupBy { it.distrito }
        .mapValues { it.value.size }
        .maxByOrNull { it.value }
        .let { println(it) }

    println()
    println("Distrito que tiene más accidentes con Alcohol y Drogas")
    accidentes.filter { it.positivoAlcohol && it.positivoDrogas }
        .groupBy { it.distrito }
        .mapValues { it.value.size }
        .maxByOrNull { it.value }
        .let { println(it) }

}