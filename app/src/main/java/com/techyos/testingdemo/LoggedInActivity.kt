package com.techyos.testingdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_logged_in.*
import kotlinx.android.synthetic.main.activity_main.*

class LoggedInActivity : AppCompatActivity() {

    companion object {
        val EXTRA_EMAIL = "extra_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        var who = intent.extras?.get(EXTRA_EMAIL) ?: getString(R.string.guest)
        textWelcome.text = getString(R.string.welcome, who)

        button.setOnClickListener { finish() }
    }
}
