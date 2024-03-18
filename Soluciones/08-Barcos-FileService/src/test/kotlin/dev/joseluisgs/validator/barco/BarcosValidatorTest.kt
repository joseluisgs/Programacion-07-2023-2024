package dev.joseluisgs.validator.barco

import dev.joseluisgs.dev.joseluisgs.validator.barco.BarcosValidator
import dev.joseluisgs.exceptions.barco.BarcoExceptions
import dev.joseluisgs.models.barco.Atunero
import dev.joseluisgs.models.barco.Marisquero
import org.junit.jupiter.api.*
import java.time.LocalDate

class BarcosValidatorTest {

    private lateinit var validator: BarcosValidator
    private lateinit var atuneroValido: Atunero
    private lateinit var marisqueroValido: Marisquero
    private lateinit var fechaValida: LocalDate

    @BeforeEach
    fun setUp() {
        validator = BarcosValidator()
        fechaValida = LocalDate.of(2020, 1, 1)
        atuneroValido = Atunero("ATN-1234", "Atunero1", "Patron1", 200.0, fechaValida, 3000.0)
        marisqueroValido = Marisquero("MRS-5678", "Marisquero1", "Patron2", 300.0, fechaValida, 15)
    }

    @Test
    fun `validate matricula with valid Atunero matricula should not throw`() {
        assertDoesNotThrow {
            validator.validate(atuneroValido)
        }
    }

    @Test
    fun `validate matricula with invalid Atunero matricula should throw`() {
        val atuneroInvalido = Atunero(
            matricula = "XXX-1234",
            nombre = "Atunero1",
            patron = "Patron1",
            cargaActual = 200.0,
            fechaIncorporacion = fechaValida,
            cargaMaxima = 3000.0
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(atuneroInvalido)
        }
    }

    @Test
    fun `validate matricula with valid Marisquero matricula should not throw`() {
        Assertions.assertDoesNotThrow {
            validator.validate(marisqueroValido)
        }
    }

    @Test
    fun `validate matricula with invalid Marisquero matricula should throw`() {
        val marisqueroInvalido = Marisquero(
            matricula = "XXX-5678",
            nombre = "Marisquero1",
            patron = "Patron2",
            cargaActual = 300.0,
            fechaIncorporacion = fechaValida,
            experienciaTripulacion = 15
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(marisqueroInvalido)
        }
    }

    @Test
    fun `validate nombre with valid nombre should not throw`() {
        assertDoesNotThrow {
            validator.validate(atuneroValido)
        }
    }

    @Test
    fun `validate nombre with invalid nombre should throw`() {
        val barcoInvalido = Atunero(
            matricula = "ATN-1234",
            nombre = "!",
            patron = "Patron1",
            cargaActual = 200.0,
            fechaIncorporacion = fechaValida,
            cargaMaxima = 3000.0
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(barcoInvalido)
        }
    }

    @Test
    fun `validate patron with valid patron should not throw`() {
        Assertions.assertDoesNotThrow {
            validator.validate(atuneroValido)
        }
    }

    @Test
    fun `validate patron with invalid patron should throw`() {
        val barcoInvalido = Atunero(
            matricula = "ATN-1234",
            nombre = "Atunero1",
            patron = "abcd",
            cargaActual = 200.0,
            fechaIncorporacion = fechaValida,
            cargaMaxima = 3000.0
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(barcoInvalido)
        }
    }

    @Test
    fun `validate cargaActual with valid cargaActual should not throw`() {
        assertDoesNotThrow {
            validator.validate(atuneroValido)
        }
    }

    @Test
    fun `validate cargaActual with invalid cargaActual should throw`() {
        val barcoInvalido = Atunero(
            matricula = "ATN-1234",
            nombre = "Atunero1",
            patron = "Patron1",
            cargaActual = 99.9,
            fechaIncorporacion = fechaValida,
            cargaMaxima = 3000.0
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(barcoInvalido)
        }
    }

    @Test
    fun `validate fechaIncorporacion with valid fechaIncorporacion should not throw`() {
        assertDoesNotThrow {
            validator.validate(atuneroValido)
        }
    }

    @Test
    fun `validate fechaIncorporacion with invalid fechaIncorporacion should throw`() {
        val barcoInvalido = Atunero(
            matricula = "ATN-1234",
            nombre = "Atunero1",
            patron = "Patron1",
            cargaActual = 200.0,
            fechaIncorporacion = LocalDate.of(1999, 1, 1),
            cargaMaxima = 3000.0
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(barcoInvalido)
        }
    }

    @Test
    fun `validate cargaMaxima with valid cargaMaxima should not throw`() {
        Assertions.assertDoesNotThrow {
            validator.validate(atuneroValido)
        }
    }

    @Test
    fun `validate cargaMaxima with invalid cargaMaxima should throw`() {
        val barcoInvalido = Atunero(
            matricula = "ATN-1234",
            nombre = "Atunero1",
            patron = "Patron1",
            cargaActual = 200.0,
            fechaIncorporacion = fechaValida,
            cargaMaxima = 999.9
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(barcoInvalido)
        }
    }

    @Test
    fun `validate experiencia with valid experiencia should not throw`() {
        assertDoesNotThrow {
            validator.validate(marisqueroValido)
        }
    }

    @Test
    fun `validate experiencia with invalid experiencia should throw`() {
        val barcoInvalido = Marisquero(
            matricula = "MRS-5678",
            nombre = "Marisquero1",
            patron = "Patron2",
            cargaActual = 300.0,
            fechaIncorporacion = fechaValida,
            experienciaTripulacion = 9
        )
        assertThrows<BarcoExceptions.BarcoNotValid> {
            validator.validate(barcoInvalido)
        }
    }
}
