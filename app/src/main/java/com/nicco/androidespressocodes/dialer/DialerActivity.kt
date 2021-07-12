package com.nicco.androidespressocodes.dialer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nicco.androidespressocodes.R

private const val REQUEST_CODE_PICK = 16

class DialerActivity : AppCompatActivity() {

    private var mCallerNumber: EditText? = null

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                mCallerNumber!!.setText(
                    result.data!!.extras!!
                        .getString(KEY_PHONE_NUMBER)
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialer)
        mCallerNumber = findViewById<View>(R.id.edit_text_caller_number) as EditText
    }

    fun onCall(view: View?) {
        val hasCallPhonePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        if (hasCallPhonePermission)
            startActivity(createCallIntentFromNumber())
        else {
            ActivityCompat.requestPermissions(
                this@DialerActivity,
                arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE_PICK
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == REQUEST_CODE_PICK) {
            startActivity(createCallIntentFromNumber())
        } else {
            Toast.makeText(
                this,
                R.string.warning_call_phone_permission,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onPickContact(view: View?) {
        val intent = Intent(this, ContactsActivity::class.java)
        resultLauncher.launch(intent)
    }

    private fun createCallIntentFromNumber(): Intent? {
        val intentToCall = Intent(Intent.ACTION_CALL)
        val number = mCallerNumber!!.text.toString()
        intentToCall.data = Uri.parse("tel:$number")
        return intentToCall
    }
}