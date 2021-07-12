package com.nicco.androidespressocodes.dialer

import android.content.Intent
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.nicco.androidespressocodes.R

val KEY_PHONE_NUMBER = "key_phone_number"

class ContactsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setResult(RESULT_OK, createResultData("896-745-231"))
        finish()
    }

    @VisibleForTesting
    fun createResultData(phoneNumber: String?): Intent? {
        val resultData = Intent()
        resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber)
        return resultData
    }
}