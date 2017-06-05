package com.techyos.testingdemo


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.*


class EmailValidatorTestJUnit5DynamicKotlin {

    private var validator: EmailValidator? = null

    @BeforeEach
    fun init() {
        validator = EmailValidator()
    }

    @TestFactory
    @DisplayName("Using actual values")
    internal fun actualValues(): Collection<DynamicTest> {
        return Arrays.asList(
                noException("address@domain.com", true),
                noException("bademail", false)
        )
    }

    @TestFactory
    @DisplayName("Cause exceptions")
    internal fun causesException(): Collection<DynamicTest> {
        return Arrays.asList(
                withException("bad", "Input is too short"),
                withException(null, "Input cannot be null")
        )
    }

    private fun noException(input: String, expected: Boolean): DynamicTest {
        val displayName = String.format(Locale.getDefault(), "With input <%s>, expects <%s>", input, java.lang.Boolean.toString(expected))
        return DynamicTest.dynamicTest(displayName) {
            val valid = validator!!.validate(input)
            assertEquals(expected, valid)
        }
    }

    private fun withException(input: String?, exceptionMessage: String): DynamicTest {
        val displayName = String.format(Locale.getDefault(), "With input <%s>, expects exception with message <%s>",
                input, exceptionMessage)

        return DynamicTest.dynamicTest(displayName) {
            val exception = assertThrows(IllegalArgumentException::class.java) { validator!!.validate(input) }

            // THEN
            assertEquals(exceptionMessage, exception.message)
        }
    }

}
