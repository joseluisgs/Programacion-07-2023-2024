package services.barco

import dev.joseluisgs.dev.joseluisgs.validator.barco.BarcosValidator
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
    }

    @Test
    fun getByPatron() {
    }

    @Test
    fun getByMatricula() {
    }

    @Test
    fun save() {
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