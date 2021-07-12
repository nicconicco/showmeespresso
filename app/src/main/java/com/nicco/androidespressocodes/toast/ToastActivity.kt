package com.nicco.androidespressocodes.toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nicco.androidespressocodes.R

class ToastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast2)
        val btn = findViewById<View>(R.id.buttonToast) as Button
        btn.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_sendo_mostrado), Toast.LENGTH_SHORT).show()
        }
    }
}