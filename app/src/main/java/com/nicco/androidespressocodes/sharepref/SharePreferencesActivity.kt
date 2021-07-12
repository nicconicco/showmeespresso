package com.nicco.androidespressocodes.sharepref

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.nicco.androidespressocodes.R
import com.nicco.androidespressocodes.sharepref.SharedPreferencesUtil.changeStatus
import com.nicco.androidespressocodes.sharepref.SharedPreferencesUtil.getStatus

class SharePreferencesActivity : AppCompatActivity() {

    val subTitle by lazy { findViewById<TextView>(R.id.sub_title) }
    val btn by lazy { findViewById<Button>(R.id.btn) }
    var status: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preferences)

        subTitle.text = getStatus(this).toString()

        btn.setOnClickListener {
            status = !status
            changeStatus(this, status)
            subTitle.text = getStatus(this).toString()
        }
    }
}

object SharedPreferencesUtil {

    private const val STATE_PREF = "STATE_PREF"

    fun changeStatus(context: Context, boolean: Boolean) {
        val editor = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE).edit()
        editor.putBoolean(STATE_PREF, boolean)
        editor.apply()
    }

    fun getStatus(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        return sharedPreferences.getBoolean(STATE_PREF, true)
    }
}