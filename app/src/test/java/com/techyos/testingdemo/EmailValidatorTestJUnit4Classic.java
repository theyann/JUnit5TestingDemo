package com.techyos.testingdemo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.failBecauseExceptionWasNotThrown;

public class EmailValidatorTestJUnit4Classic {

    private EmailValidator validator;

    @Before
    public void init() {
        validator = new EmailValidator();
    }

    @Test
    public void validateTestWithValidEmail() {
        // GIVEN

        String email = "address@domain.com";

        // WHEN

        boolean valid = validator.validate(email);

        // THEN

        assertThat(valid).isTrue();
    }

    @Test
    public void validateTestWithInvalidEmail() {
        // GIVEN

        String email = "bademail";

        // WHEN

        boolean valid = validator.validate(email);

        // THEN

        assertThat(valid).isFalse();
    }

    @Test
    public void validateTestWithValueTooShort() {
        // GIVEN

        String email = "bad";

        // WHEN
        try {
            validator.validate(email);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            // THEN
            assertThat(e).hasMessage("Input is too short");
        }
    }

    @Test
    public void validateTestWithNull() {
        // GIVEN

        String email = null;

        // WHEN
        try {
            validator.validate(email);
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            // THEN
            assertThat(e).hasMessage("Input cannot be null");
        }
    }

    @Test
    @Ignore("This is useless right now")
    public void uselessTest() {
        assertThat(true).isTrue();
    }
}
