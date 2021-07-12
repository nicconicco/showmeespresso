package com.nicco.androidespressocodes.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.nicco.androidespressocodes.R


class WebViewActivity : AppCompatActivity() {
    val KEY_URL_TO_LOAD = "KEY_URL_TO_LOAD"

    @VisibleForTesting
    protected val WEB_FORM_URL = "file:///android_asset/web_form.html"

    private var mWebView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        mWebView = findViewById<View>(R.id.web_view) as WebView
        mWebView!!.settings.javaScriptEnabled = true
        urlFromIntent(intent)?.let { mWebView!!.loadUrl(it) }
        mWebView!!.requestFocus()
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
    }

    private fun urlFromIntent(@NonNull intent: Intent): String? {
        val url = intent.getStringExtra(KEY_URL_TO_LOAD)
        return if (!TextUtils.isEmpty(url)) url else WEB_FORM_URL
    }
}