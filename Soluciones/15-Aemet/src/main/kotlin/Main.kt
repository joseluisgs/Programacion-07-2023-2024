package dev.joseluisgs

import dev.joseluisgs.models.Informe
import dev.joseluisgs.services.MeteoService
import java.time.LocalTime

fun main() {
    println("Hello World!")
    val service = MeteoService()
    // Temperatura máxima por día y lugar
    service.mediciones.groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            println("Temperatura máxima el $fecha")
            mediciones.groupBy { it.ciudad }
                .forEach { (ciudad, mediciones) ->
                    val max = mediciones.maxOf { it.temperaturaMaxima }
                    println("En $ciudad: $max")
                }
        }
    // Temperatura mínima por día y provincia, indicando lugar
    service.mediciones.groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            println("Temperatura mínima el $fecha")
            mediciones.groupBy { it.provincia }
                .forEach { (provincia, mediciones) ->
                    val min = mediciones.minByOrNull { it.temperaturaMinima }
                    println("En $provincia: $min")
                }
        }

    // Temperatura media por provincia
    service.mediciones.groupBy { it.provincia }
        .forEach { (provincia, mediciones) ->
            println("Temperatura media en $provincia")
            mediciones.groupBy { it.fecha }
                .forEach { (fecha, mediciones) ->
                    val media = mediciones.map { it.temperaturaMaxima }.average()
                    println("El $fecha: $media")
                }
        }

    // Listado de precipitación media por día y provincia
    service.mediciones.groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            println("Precipitación media el $fecha")
            mediciones.groupBy { it.provincia }
                .forEach { (provincia, mediciones) ->
                    val media = mediciones.map { it.precipitacion }.average()
                    println("En $provincia: $media")
                }
        }

    // Numero de lugares en el que llovío por día y provincia
    service.mediciones.groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            println("Lugares en los que llovío el $fecha")
            mediciones.groupBy { it.provincia }
                .forEach { (provincia, mediciones) ->
                    val count = mediciones.count { it.precipitacion > 0 }
                    println("En $provincia: $count")
                }
        }

    // Temperatura média de la provincia de Madrid
    service.mediciones.filter { it.provincia == "Madrid" }
        .groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            val mediaMaxima = mediciones.map { it.temperaturaMaxima }.average()
            val mediaMinima = mediciones.map { it.temperaturaMinima }.average()
            println("Temperatura media en Madrid el $fecha, Maxima: $mediaMaxima, Minima: $mediaMinima")
        }

    // Media de temperatura máxima total
    val mediaMaxima = service.mediciones.map { it.temperaturaMaxima }.average()
    println("Temperatura media máxima total: $mediaMaxima")

    // Media de temperatura mínima total
    val mediaMinima = service.mediciones.map { it.temperaturaMinima }.average()
    println("Temperatura media mínima total: $mediaMinima")

    // Lugares donde la máxima ha sido antes de las 15:00 por día
    service.mediciones.groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            println("Lugares donde la máxima ha sido antes de las 15:00 el $fecha")
            mediciones.filter { it.horaTemperaturaMaxima.isBefore(LocalTime.of(15, 0)) }
                .groupBy { it.ciudad }
                .forEach { (ciudad, mediciones) ->
                    println("En $ciudad, ${mediciones.first().horaTemperaturaMaxima}")
                }
        }

    // Lugares donde la mínima ha sido después de las 17:30 por día
    service.mediciones.groupBy { it.fecha }
        .forEach { (fecha, mediciones) ->
            println("Lugares donde la mínima ha sido después de las 17:30 el $fecha")
            mediciones.filter { it.horaTemperaturaMinima.isAfter(LocalTime.of(17, 30)) }
                .groupBy { it.ciudad }
                .forEach { (ciudad, mediciones) ->
                    println("En $ciudad, ${mediciones.first().horaTemperaturaMinima}")
                }
        }

    val temperaturaMediaMaximaMadrid = service.mediciones.filter { it.provincia == "Madrid" }
        .map { it.temperaturaMaxima }
        .average()
    println("Temperatura media máxima en Madrid: $temperaturaMediaMaximaMadrid")

    val temperaturaMediaMinimaMadrid = service.mediciones.filter { it.provincia == "Madrid" }
        .map { it.temperaturaMinima }
        .average()

    println("Temperatura media mínima en Madrid: $temperaturaMediaMinimaMadrid")

    val temperaturaMaximaMadrid = service.mediciones.filter { it.provincia == "Madrid" }
        .maxByOrNull { it.temperaturaMaxima }
    println("Temperatura máxima en Madrid: $temperaturaMaximaMadrid")

    val temperaturaMinimaMadrid = service.mediciones.filter { it.provincia == "Madrid" }
        .minByOrNull { it.temperaturaMinima }
    println("Temperatura mínima en Madrid: $temperaturaMinimaMadrid")

    val precipitacionMadrid = service.mediciones.filter { it.provincia == "Madrid" && it.precipitacion > 0 }
        .groupBy { it.fecha }
        .map { (fecha, mediciones) ->
            fecha.toString() to Pair(
                mediciones.count(),
                mediciones.map { it.precipitacion }.average()
            )
        }
    println("Días de precipitación en Madrid: $precipitacionMadrid")

    val informe = Informe(
        provincia = "Madrid",
        temperaturaMediaMaxima = mediaMaxima,
        temperaturaMediaMinima = mediaMinima,
        temperaturaMaxima = temperaturaMaximaMadrid?.temperaturaMaxima ?: 0.0,
        temperaturaMinima = temperaturaMinimaMadrid?.temperaturaMinima ?: 0.0,
        precipitacionFechaNumLugaresMedia = precipitacionMadrid
    )

    service.exportToJson(informe)
    service.exportToXml(informe)
}