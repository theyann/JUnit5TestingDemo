package com.techyos.testingdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorJava {

    private static final String EMAIL_STRING_PATTERN = "^[\\w.+-]+@[\\w.-]+.[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_STRING_PATTERN);

    public boolean validate(CharSequence input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        } else if (input.length() < 5) {
            throw new IllegalArgumentException("Input is too short");
        } else {
            Matcher matcher = EMAIL_PATTERN.matcher(input);
            return matcher.matches();
        }
    }


}
