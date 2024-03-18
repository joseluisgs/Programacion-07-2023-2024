package dev.joseluisgs

import import.PokedexDto
import import.toPokemon
import kotlinx.serialization.json.Json

fun main() {
    println("Pokedex")

    val file = object {}.javaClass.classLoader.getResource("pokemon.json")
    val pokedex = file?.readText() ?: throw Exception("No se ha podido leer el fichero")
    val pokedexDto = Json.decodeFromString<PokedexDto>(pokedex)
    val pokemonList = pokedexDto.pokemonDto

    println("Pokemons: ${pokemonList.size}")

    pokemonList.take(10).forEach {
        println(it)
    }

    val pokemons = pokemonList.map { it.toPokemon() }
    pokemons.forEach {
        println("${it.id}: $it")
    }

    // pokemon mas pesado
    val pokemonMasPesado = pokemons.maxByOrNull { it.weight }
    println("Pokemon mas pesado: $pokemonMasPesado")

    // pokemon mas ligero
    val pokemonMasLigero = pokemons.minByOrNull { it.weight }
    println("Pokemon mas ligero: $pokemonMasLigero")

    // Pokemnos con mas evoluciones
    val pokemonMasEvolucionado = pokemons.maxByOrNull { it.nextEvolution?.size ?: 0 }
    println("Pokemon mas evolucionado: $pokemonMasEvolucionado")

    // Pokemnos con menos evoluciones
    val pokemonMenosEvolucionado = pokemons.minByOrNull { it.nextEvolution?.size ?: 0 }
    println("Pokemon menos evolucionado: $pokemonMenosEvolucionado")

    // Pokemnos con mas debilidades
    val pokemonMasDebil = pokemons.maxByOrNull { it.weaknesses.size }
    println("Pokemon mas debil: $pokemonMasDebil")

    // Pokemon electrico
    val pokemonElectric = pokemons.filter { it.type.contains("Electric") }
    println("Pokemon electricos: ${pokemonElectric.size}")

    // Pikachu
    val pikachu = pokemons.find { it.name == "Pikachu" }
    println("Pikachu: $pikachu")

    // Agrupados por tipo
    val pokemonsByType = pokemons.groupBy { it.type }
    pokemonsByType.forEach { (type, pokemons) ->
        println("Tipo: $type, Pokemons: ${pokemons.size}")
    }

    // Agrupados por debilidades
    val pokemonsByWeaknesses = pokemons.flatMap { it.weaknesses }.distinct().associateWith { weakness ->
        pokemons.filter { it.weaknesses.contains(weakness) }
    }
    pokemonsByWeaknesses.forEach { (weakness, pokemons) ->
        println("Debilidad: $weakness, Pokemons: ${pokemons.size}")
    }

    // Debiliades y numero de pokemons
    val weaknessCount = pokemons
        .flatMap { it.weaknesses }
        .groupingBy { it }.eachCount()
    weaknessCount.forEach { (weakness, count) ->
        println("Debilidad: $weakness, Pokemons: $count")
    }

}