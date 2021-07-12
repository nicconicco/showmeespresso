package com.nicco.androidespressocodes.image

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.nicco.androidespressocodes.R


open class ImageViewerActivity: AppCompatActivity() {

    @VisibleForTesting
    protected val KEY_IMAGE_DATA = "data"

    private var mImageView: ImageView? = null

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val extras = result.data?.extras
                val imageBitmap = extras?.get(KEY_IMAGE_DATA) as Bitmap?
                mImageView!!.setImageBitmap(imageBitmap)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        mImageView = findViewById<View>(R.id.imageView) as ImageView
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(takePictureIntent)
    }

    fun onOpenCamera(view: View?) {
        dispatchTakePictureIntent()
    }
}