package com.techyos.testingdemo

class EmailValidator {

    companion object {
        private val EMAIL_REGEX = """^[\w.+-]+@[\w.-]+.[a-zA-Z]{2,6}$""".toRegex()
    }

    fun validate(input: CharSequence?): Boolean {
        return if (input == null) throw IllegalArgumentException("Input cannot be null")
        else if (input.length < 5) throw IllegalArgumentException("Input is too short")
        else EMAIL_REGEX.matches(input)
    }

}
