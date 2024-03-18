package dev.joseluisgs.dev.joseluisgs.services

import dev.joseluisgs.dev.joseluisgs.models.Accidente
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalTime

object AccidentesService {
    var accidentes: List<Accidente> = mutableListOf()
        get() = field.toList()

    init {
        val fileStream = ClassLoader.getSystemResourceAsStream("2023_Accidentalidad.csv")
            ?: throw IllegalStateException("No se encontró el archivo 2023_Accidentalidad")
        accidentes = parseAccidentes(fileStream).toMutableList()

    }

    private fun parseAccidentes(fileStream: InputStream): List<Accidente> {
        val accidentes = mutableListOf<Accidente>()
        fileStream.bufferedReader().useLines { lines ->
            lines.drop(1)
                //.take(2)
                .forEach {
                    val campos = it.split(";")
                    val accidente = parseAccidente(campos)
                    accidentes.add(accidente)
                }
        }
        return accidentes
    }

    private fun parseAccidente(campos: List<String>): Accidente {
        return Accidente(
            numExpediente = campos[0], // numExpediente
            fecha = parseFecha(campos[1]), // fecha
            hora = parseHora(campos[2]), // hora
            calle = campos[3],
            distrito = campos[6],
            tipoAccidente = campos[7],
            estadoMeteorologico = campos[8],
            tipoVehiculo = campos[9],
            tipoPersona = campos[10],
            rangoEdad = campos[11],
            sexo = parseSexo(campos[12]), // sexo
            lesividad = campos[14],
            positivoAlcohol = parseAlcohol(campos[17]), // alcohol
            positivoDrogas = parseDrogas(campos[18]), // drogas
        )
    }

    private fun parseHora(valor: String): LocalTime {
        val campos = valor.split(":")
        // hh:mm:ss
        return LocalTime.of(campos[0].toInt(), campos[1].toInt(), campos[2].toInt())
    }

    private fun parseFecha(valor: String): LocalDate {
        val campos = valor.split("/")
        // aaaa,mm,dd
        return LocalDate.of(campos[2].toInt(), campos[1].toInt(), campos[0].toInt())
    }

    private fun parseDrogas(valor: String): Boolean {
        return valor == "1"
    }

    private fun parseAlcohol(valor: String): Boolean {
        return valor == "S"
    }

    private fun parseSexo(valor: String): Accidente.Sexo {
        return when (valor.lowercase()) {
            "hombre" -> Accidente.Sexo.HOMBRE
            "mujer" -> Accidente.Sexo.MUJER
            else -> Accidente.Sexo.NO_ASIGNADO
        }
    }
}