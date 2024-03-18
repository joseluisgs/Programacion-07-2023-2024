package services.barco

import dev.joseluisgs.dev.joseluisgs.validator.barco.BarcosValidator
import dev.joseluisgs.exceptions.barco.BarcoExceptions
import dev.joseluisgs.models.barco.Atunero
import dev.joseluisgs.models.barco.Barco
import dev.joseluisgs.repositories.barcos.BarcosRepository
import dev.joseluisgs.repositories.cache.Cache
import dev.joseluisgs.services.barcos.BarcosServiceImpl
import dev.joseluisgs.services.storage.base.FileStorage
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class BarcosServiceImplTest {
    // Mocks
    @MockK
    private lateinit var repository: BarcosRepository

    @MockK
    private lateinit var storage: FileStorage<Barco>

    @MockK
    private lateinit var cache: Cache<Barco, String>

    @MockK
    private lateinit var validator: BarcosValidator

    // system under test
    @InjectMockKs
    private lateinit var service: BarcosServiceImpl


    @Test
    fun `getAll returns all barcos`() {
        // Arrange
        val expectedBarcos = listOf(
            Atunero(
                "123456789",
                "Barco 1",
                "Pepe",
                100.0,
                LocalDate.now(),
                1000.0,
            )
        )

        // Actuamos como mock
        every { repository.getAll() } returns expectedBarcos

        // Ejecutamos el método
        val actualBarcos = service.getAll(null, null)

        // Assert
        assertEquals(expectedBarcos, actualBarcos)

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { repository.getAll() }
    }

    @Test
    fun getByName() {
        // Arrange
        val expectedBarco = Atunero(
            "123456789",
            "Barco 1",
            "Pepe",
            100.0,
            LocalDate.now(),
            1000.0,
        )

        // Actuamos como mock
        every { repository.getByName("Barco 1") } returns listOf(expectedBarco)

        // Ejecutamos el método
        val actualBarco = service.getByName("Barco 1")

        // Assert
        assertEquals(expectedBarco, actualBarco[0])

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { repository.getByName("Barco 1") }
    }

    @Test
    fun getByPatron() {
        // Arrange
        val expectedBarco = Atunero(
            "123456789",
            "Barco 1",
            "Pepe",
            100.0,
            LocalDate.now(),
            1000.0,
        )

        // Actuamos como mock
        every { repository.getByPatron("Pepe") } returns listOf(expectedBarco)

        // Ejecutamos el método
        val actualBarco = service.getByPatron("Pepe")

        // Assert
        assertEquals(expectedBarco, actualBarco[0])

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { repository.getByPatron("Pepe") }
    }

    @Test
    fun getByMatriculaCacheFound() {
        // Arrange
        val expectedBarco = Atunero(
            "123456789",
            "Barco 1",
            "Pepe",
            100.0,
            LocalDate.now(),
            1000.0,
        )

        // Actuamos como mock
        every { cache.get("123456789") } returns expectedBarco

        // Ejecutamos el método
        val actualBarco = service.getByMatricula("123456789")

        // Assert
        assertEquals(expectedBarco, actualBarco)

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { cache.get("123456789") }
        verify(exactly = 0) { repository.getById("123456789") }
    }

    @Test
    fun getByMatriculaRepositoryFound() {
        // Arrange
        val expectedBarco = Atunero(
            "123456789",
            "Barco 1",
            "Pepe",
            100.0,
            LocalDate.now(),
            1000.0,
        )

        // Actuamos como mock
        every { cache.get("123456789") } returns null
        every { repository.getById("123456789") } returns expectedBarco
        every { cache.put("123456789", expectedBarco) } returns Unit

        // Ejecutamos el método
        val actualBarco = service.getByMatricula("123456789")

        // Assert
        assertEquals(expectedBarco, actualBarco)

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { cache.get("123456789") }
        verify(exactly = 1) { repository.getById("123456789") }
        verify(exactly = 1) { cache.put("123456789", expectedBarco) }
    }

    @Test
    fun getByMatriculaNotFound() {
        // Arrange
        // Actuamos como mock
        every { cache.get("123456789") } returns null
        every { repository.getById("123456789") } returns null

        // Ejecutamos el método
        val exception = assertThrows<BarcoExceptions.BarcoNotFound> {
            service.getByMatricula("123456789")
        }

        // Assert
        assertEquals("No se encontró el barco: 123456789", exception.message)

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { cache.get("123456789") }
        verify(exactly = 1) { repository.getById("123456789") }
    }

    @Test
    fun saveOk() {
        // Arrange
        val barco = Atunero(
            "123456789",
            "Barco 1",
            "Pepe",
            100.0,
            LocalDate.now(),
            1000.0,
        )

        // Actuamos como mock
        every { validator.validate(barco) } returns Unit
        every { cache.get("123456789") } returns null
        every { repository.getById("123456789") } returns null
        every { repository.save(barco) } returns barco
        every { cache.put("123456789", barco) } returns Unit

        // Ejecutamos el método
        val actualBarco = service.save(barco)

        // Assert
        assertEquals(barco, actualBarco)

        // Verificamos que se ha llamado al método
        verify(exactly = 1) { cache.get("123456789") }
        verify(exactly = 1) { repository.save(barco) }
        verify(exactly = 1) { cache.put("123456789", barco) }
        verify(exactly = 1) { validator.validate(barco) }
        verify(exactly = 1) { repository.getById("123456789") }
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun exportToFile() {
    }

    @Test
    fun importFromFile() {
    }
}