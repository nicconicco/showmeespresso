package com.nicco.androidespressocodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    val etLogin by lazy { findViewById<EditText>(R.id.etLogin) }
    val etSenha by lazy { findViewById<EditText>(R.id.etSenha) }
    val btLogin by lazy { findViewById<Button>(R.id.btLogin) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        btLogin.setOnClickListener { onClickLogin() }
    }

    private fun onClickLogin() {
        val login = etLogin.text.toString()
        val senha = etSenha.text.toString()

        if(login.isEmpty() && senha.isEmpty()) {
            Toast.makeText(this, "Login e Senha nao podem estar vazios", Toast.LENGTH_LONG).show()
        } else if(login.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Login ou Senha nao podem estar vazios", Toast.LENGTH_LONG).show()
        } else {
            startActivity(Intent(this, AnotherActivity::class.java))
        }
    }
}