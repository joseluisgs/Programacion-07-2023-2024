package dev.joseluisgs

import dev.joseluisgs.dto.InformeDto
import dev.joseluisgs.dto.PersonasDto
import dev.joseluisgs.mappers.*
import dev.joseluisgs.models.Docente
import dev.joseluisgs.models.Estudiante
import import.ImportDto
import import.toDocente
import import.toEstudiante
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.io.path.Path

fun main() {
    // Vamos a leer un fichero de la carpeta resources
    val fileName = "personas.csv"
    val file = object {}.javaClass.getResource("/$fileName")

    if (file != null) {
        println("Fichero encontrado en: ${file.path}")
        procesarFichero(file.file)
    } else {
        println("Fichero no encontrado")
    }
}

fun procesarFichero(file: String) {
    val file = File(file)
    val lista = file.readLines()
        .drop(1)
        .map { linea -> linea.split(",") }
        .map { datos ->
            PersonasDto(
                datos[0],
                datos[1],
                datos[2],
                datos[3],
                datos.getOrNull(4),
                datos.getOrNull(5)
            )
        }
        .map { it.toPersona() }

    lista.forEach { println(it) }
    /*
    - Alumno más mayor
    - Alumno más joven
    - Media de edad de alumnos
    - Media de longitud de nombre
    - Listado de agrupados por tipo
    - Listado de profesores de programación
    - Agrupados por edad, numero de alumnos
    - Agrupados por modulo, numero de profesores
    - Agrupados por edad, obtener la longitud de nombre.
    - Agrupados por edad, obtener el nombre mas largo.
    */

    // Estudiante más mayor
    val estudianteMayor = lista.filterIsInstance<Estudiante>()
        .maxByOrNull { it.edad }
    println("Estudiante más mayor: $estudianteMayor")

    // Estudiante más joven
    val estudianteMenor = lista.filterIsInstance<Estudiante>()
        .minByOrNull { it.edad }
    println("Estudiante más joven: $estudianteMenor")

    // Media de edad de alumnos
    val mediaEdad = lista.filterIsInstance<Estudiante>()
        .map { it.edad }
        .average()
    println("Media de edad de alumnos: $mediaEdad")

    // Media de longitud de nombre
    val mediaNombre = lista.map { it.nombre.length }
        .average()
    println("Media de longitud de nombre: $mediaNombre")

    // Listado de agrupados por tipo
    val agrupadosTipo = lista.groupBy { it::class.simpleName }
    println("Listado de agrupados por tipo: $agrupadosTipo")

    // Listado de profesores de programación
    val profesoresProgramacion = lista.filterIsInstance<Docente>()
        .filter { it.modulo == "Programación" }
    println("Listado de profesores de programación: $profesoresProgramacion")

    // Listado de profesores agrupados por modulo
    val profesoresAgrupadosModulo = lista.filterIsInstance<Docente>()
        .groupBy { it.modulo }
    println("Listado de profesores agrupados por modulo: $profesoresAgrupadosModulo")

    // Numero de profesores por modulo
    val numeroProfesoresModulo = lista.filterIsInstance<Docente>()
        .groupBy { it.modulo }
        .mapValues { it.value.size }
    println("Numero de profesores por modulo: $numeroProfesoresModulo")

    // Agrupados por edad, numero de alumnos
    val agrupadosEdad = lista.filterIsInstance<Estudiante>()
        .groupBy { it.edad }
        .mapValues { it.value.size }
    println("Agrupados por edad, numero de alumnos: $agrupadosEdad")

    // Agrupados por modulo, numero de profesores
    val agrupadosModulo = lista.filterIsInstance<Docente>()
        .groupBy { it.modulo }
        .mapValues { it.value.size }
    println("Agrupados por modulo, numero de profesores: $agrupadosModulo")

    // Agrupados por edad, obtener la longitud de nombre.
    val agrupadosEdadNombre = lista.filterIsInstance<Estudiante>()
        .groupBy { it.edad }
        .mapValues { it.value.map { e -> e.nombre.length } }
    println("Agrupados por edad, obtener la longitud de nombre: $agrupadosEdadNombre")

    // Agrupados por edad, obtener el nombre mas largo.
    val agrupadosEdadNombreLargo = lista.filterIsInstance<Estudiante>()
        .groupBy { it.edad }
        .mapValues { it.value.maxByOrNull { e -> e.nombre.length } }
    println("Agrupados por edad, obtener el nombre mas largo: $agrupadosEdadNombreLargo")

    // Agrupados por edad, obtener la longitud media del nombre.
    val agrupadosEdadNombreMedia = lista.filterIsInstance<Estudiante>()
        .groupBy { it.edad }
        .mapValues { it.value.map { e -> e.nombre.length }.average() }
    println("Agrupados por edad, obtener la media del nombre: $agrupadosEdadNombreMedia")


    println()
    println()

    /* val agrupadosPorTipo = lista.groupBy { it::class.simpleName }
     println("Agrupados por tipo:")
     agrupadosPorTipo.keys.forEach { tipo ->
         println("Tipo: $tipo")
         agrupadosPorTipo[tipo]?.forEach { println(it) }
     }*/

    val docentes = lista.filterIsInstance<Docente>().map { it.toDocenteDto() }
    val estudiantes = lista.filterIsInstance<Estudiante>().map { it.toEstudianteDto() }
    val informe = InformeDto(docentes, estudiantes)

    // Creo el JSON prettyprint
    val json = Json { prettyPrint = true }.encodeToString<InformeDto>(informe)
    println(json)

    // Salvamos en el directorio data
    val actualDir = System.getProperty("user.dir")
    println("El directorio actual es: $actualDir")

    val databaseDir = Path(actualDir, "data")
    // Si no existe el directorio lo creamos
    if (!databaseDir.toFile().exists()) {
        databaseDir.toFile().mkdir()
    }
    val fichero = File(databaseDir.toFile(), "informe.json")
    fichero.writeText(json)

    // Leemos el fichero JSON
    val informeLeido = Json.decodeFromString<InformeDto>(fichero.readText())

    val personas = informeLeido.docentes.map { it.toDocente() } + informeLeido.estudiantes.map { it.toEstudiante() }
    println("Personas leidas:")
    personas.forEach { println(it) }

    val importDto = Json.decodeFromString<ImportDto>(fichero.readText())
    val personasImport = importDto.docentes.map { it.toDocente() } + importDto.estudiantes.map { it.toEstudiante() }
    println("Personas leidas:")
    personasImport.forEach { println(it) }


}
