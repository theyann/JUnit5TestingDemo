package com.techyos.testingdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.failBecauseExceptionWasNotThrown;

@RunWith(Parameterized.class)
public class EmailValidatorTestJUnit4Parameterized {

    public static class EmailValidationParam {
        public final String input;
        public final boolean expected;
        public final String exceptionMessage;

        public EmailValidationParam(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
            exceptionMessage = null;
        }

        public EmailValidationParam(String input, String exceptionMessage) {
            this.input = input;
            this.exceptionMessage = exceptionMessage;
            expected = false;
        }

        @Override
        public String toString() {
            return "EmailValidationParam with input <" + input + ">, expects <" + expected + ">";
        }
    }

    static Collection<Object[]> scenarios;

    static {
        scenarios = Arrays.asList(new Object[][]{
                {new EmailValidationParam("address@domain.com", true)},
                {new EmailValidationParam("bademail", false)},
                {new EmailValidationParam("bad", "Input is too short")},
                {new EmailValidationParam(null, "Input cannot be null")}
        });
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return scenarios;
    }

    private final EmailValidationParam param;
    private EmailValidator validator;

    public EmailValidatorTestJUnit4Parameterized(EmailValidationParam param) {
        this.param = param;
    }

    @Before
    public void init() {
        validator = new EmailValidator();
    }

    @Test
    public void validateTest() {
        // GIVEN

        String email = param.input;

        // WHEN
        try {
            boolean valid = validator.validate(email);
            if (param.exceptionMessage == null) {
                assertThat(valid).isEqualTo(param.expected);
            } else {
                failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
            }
        } catch (IllegalArgumentException e) {
            // THEN
            assertThat(e).hasMessage(param.exceptionMessage);
        }


    }

}
