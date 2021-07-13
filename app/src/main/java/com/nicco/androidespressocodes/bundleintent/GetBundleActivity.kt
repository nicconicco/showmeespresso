package com.nicco.androidespressocodes.bundleintent

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nicco.androidespressocodes.R


class GetBundleActivity : AppCompatActivity() {
    val loginBundle by lazy { findViewById<TextView>(R.id.loginBundle) }
    val emailBundle by lazy { findViewById<TextView>(R.id.emailBundle) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_bundle)

        intent.apply {
            loginBundle.text = "Hola!, "+ intent.getStringExtra(LOGIN)
            emailBundle.text = "Seu email: "+intent.getStringExtra(EMAIL)
        }
    }
}