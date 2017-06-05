package com.techyos.testingdemo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TimedUnit
public class EmailValidatorTestJUnit5Dynamic {

    private EmailValidator validator;

    @BeforeEach
    public void init() {
        validator = new EmailValidator();
    }

    @TestFactory
    @DisplayName("Using actual values")
    Collection<DynamicTest> actualValues() {
        return Arrays.asList(
                noException("address@domain.com", true),
                noException("bademail", false)
        );
    }

    @TestFactory
    @DisplayName("Cause exceptions")
    Collection<DynamicTest> causesException() {
        return Arrays.asList(
                withException("bad", "Input is too short"),
                withException(null, "Input cannot be null")
        );
    }

    private DynamicTest noException(final String input, final boolean expected) {
        String displayName = String.format(Locale.getDefault(), "With input <%s>, expects <%s>", input, Boolean.toString(expected));
        return DynamicTest.dynamicTest(displayName, new Executable() {
            @Override
            public void execute() throws Throwable {
                boolean valid = validator.validate(input);
                assertEquals(expected, valid);
            }
        });
    }

    private DynamicTest withException(final String input, final String exceptionMessage) {
        String displayName = String.format(Locale.getDefault(), "With input <%s>, expects exception with message <%s>",
                input, exceptionMessage);

        return DynamicTest.dynamicTest(displayName, new Executable() {
            @Override
            public void execute() throws Throwable {
                Throwable exception = assertThrows(IllegalArgumentException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        validator.validate(input);
                    }
                });

                // THEN
                assertEquals(exceptionMessage, exception.getMessage());

            }
        });
    }

}
