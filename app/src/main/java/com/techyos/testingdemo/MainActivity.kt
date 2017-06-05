package com.techyos.testingdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val emailWatcher = EmailTextWatcher()
    private val passwordWatcher = PasswordTextWatcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editEmail.addTextChangedListener(emailWatcher)
        editPassword.addTextChangedListener(passwordWatcher)
        buttonLogin.setOnClickListener { executeLogin() }
    }

    private fun executeLogin() {
        if (emailWatcher.emailValid && passwordWatcher.passwordValid) {
            val intent = Intent(this, LoggedInActivity::class.java)
            intent.putExtra(LoggedInActivity.EXTRA_EMAIL, editEmail.text.toString())
            startActivity(intent)
        } else {
            toast("email and or password are invalid")
        }
    }
}

class EmailTextWatcher : TextWatcher by EmptyTextWatcher {

    var emailValid = false
    var emailValidator = EmailValidator()

    override fun afterTextChanged(s: Editable?) {
        emailValid = try { emailValidator.validate(s) } catch (e: Exception) { false }
    }
}

class PasswordTextWatcher : TextWatcher by EmptyTextWatcher {

    var passwordValid = false

    override fun afterTextChanged(s: Editable?) {
        passwordValid = s?.toString()?.contains("a") ?: false
    }
}