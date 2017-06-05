package com.techyos.testingdemo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TimedUnit
public class EmailValidatorTestJUnit5Nested {

    private EmailValidator validator;

    @BeforeEach
    public void init() {
        validator = new EmailValidator();
    }


    @Nested
    @DisplayName("Using actual values")
    class UsingValues {
        @Test
        @DisplayName("Input is valid email, expects result to be true")
        void validateTestWithValidEmail() {
            // GIVEN

            String email = "address@domain.com";

            // WHEN

            boolean valid = validator.validate(email);

            // THEN

            assertTrue(valid);
        }

        @Test
        @DisplayName("Input is an invalid email, expects result to be false")
        void validateTestWithInvalidEmail() {
            // GIVEN

            String email = "bademail";

            // WHEN

            boolean valid = validator.validate(email);

            // THEN

            assertFalse(valid);
        }
    }

    @Nested
    @DisplayName("Cause exceptions")
    class CauseExceptions {
        @Test
        @DisplayName("Input is too short, expects an exception")
        void validateTestWithValueTooShort() {
            // GIVEN

            final String email = "bad";

            // WHEN
            Throwable exception = assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    validator.validate(email);
                }
            });

            // THEN
            assertEquals("Input is too short", exception.getMessage());
        }

        @Test
        @DisplayName("Input is null, expects an exception")
        void validateTestWithNull() {
            // GIVEN

            final String email = null;

            // WHEN
            Throwable exception = assertThrows(IllegalArgumentException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    validator.validate(email);
                }
            });

            // THEN
            assertEquals("Input cannot be null", exception.getMessage());
        }
    }
}
