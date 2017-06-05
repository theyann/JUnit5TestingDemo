package com.techyos.testingdemo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TimedUnit
public class EmailValidatorTestJUnit5ParamInjection {

    @Test
    @ExtendWith(ValidatorResolver.class)
    @DisplayName("Input is valid email, expects result to be true, validator injected")
    void validateTestValidatorInjected(EmailValidator validator) {
        // GIVEN

        String email = "address@domain.com";

        // WHEN

        boolean valid = validator.validate(email);

        // THEN

        assertTrue(valid);
    }

}
