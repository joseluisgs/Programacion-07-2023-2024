package dev.joseluisgs.services

import dev.joseluisgs.models.Informe
import dev.joseluisgs.models.Medicion
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalTime

class MeteoService {
    /*val mediciones: List<Medicion> = mutableListOf<Medicion>()
        get() = field.toList()*/
    private val _mediciones: MutableList<Medicion> = mutableListOf<Medicion>()
    val mediciones: List<Medicion>
        get() = _mediciones.toList()


    init {
        println("Meteo Service")
        // read all files from resources
        val meteoFiles = arrayOf("Aemet20171029.csv", "Aemet20171030.csv", "Aemet20171031.csv")
        meteoFiles.forEach {
            val file = ClassLoader.getSystemResource(it).file
            println("Reading file: $file")
            _mediciones.addAll(readFromFile(File(file)))
        }
    }


    private fun readFromFile(meteoFile: File): List<Medicion> {
        return meteoFile.readLines(charset = Charset.forName("windows-1252"))
            .map { line -> line.split(";") }
            .map { parts ->
                Medicion(
                    ciudad = parts[0].trim(), //(parts[0].toByteArray(), Charset.forName("UTF-8")),
                    provincia = parts[1].trim(),
                    fecha = parseLocalDate(meteoFile.name.substring(5)),
                    temperaturaMaxima = parts[2].toDouble(),
                    horaTemperaturaMaxima = parseLocalTime(parts[3]),
                    temperaturaMinima = parts[4].toDouble(),
                    horaTemperaturaMinima = parseLocalTime(parts[5]),
                    precipitacion = parts[6].toDouble()
                )
            }
    }

    private fun parseLocalTime(time: String): LocalTime {
        val parts = time.split(":")
        return LocalTime.of(parts[0].toInt(), parts[1].toInt())
    }

    private fun parseLocalDate(fileName: String): LocalDate {
        return LocalDate.of(
            fileName.substring(0, 4).toInt(),
            fileName.substring(4, 6).toInt(),
            fileName.substring(6, 8).toInt()
        )

    }

    fun exportToJson(informe: Informe) {
        val json = Json { prettyPrint = true; ignoreUnknownKeys = true }
        Files.createDirectories(Path.of("reports"))
        val fileName = "report-${LocalDate.now()}.json"
        val file = File("reports/$fileName")
        file.writeText(json.encodeToString<Informe>(informe))
    }

    fun exportToXml(informe: Informe) {
        val xml = XML { indent = 4 }
        Files.createDirectories(Path.of("reports"))
        val fileName = "report-${LocalDate.now()}.xml"
        val file = File("reports/$fileName")
        file.writeText(xml.encodeToString<Informe>(informe))
    }
}