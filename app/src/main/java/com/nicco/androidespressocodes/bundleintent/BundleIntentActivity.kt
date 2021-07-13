package com.nicco.androidespressocodes.bundleintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.nicco.androidespressocodes.R
import com.nicco.androidespressocodes.login.AnotherActivity

const val LOGIN = "LOGIN"
const val EMAIL = "EMAIL"

class BundleIntentActivity : AppCompatActivity() {
    val login by lazy { findViewById<EditText>(R.id.login) }
    val email by lazy { findViewById<EditText>(R.id.email) }
    val btnLogin by lazy { findViewById<Button>(R.id.btnLogin) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bundle_intent)

        btnLogin.setOnClickListener {
            Intent(this, GetBundleActivity::class.java).apply {
                putExtra(LOGIN, login.text.toString())
                putExtra(EMAIL, email.text.toString())
                startActivity(this)
            }
        }
    }
}