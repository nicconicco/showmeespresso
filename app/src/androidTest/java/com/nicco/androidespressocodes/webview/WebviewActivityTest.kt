package com.nicco.androidespressocodes.webview

import android.content.Intent
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.DriverAtoms.*
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WebviewActivityTest {

    @Rule
    @JvmField
    var mActivity: ActivityTestRule<WebViewActivity> = object : ActivityTestRule<WebViewActivity>(
        WebViewActivity::class.java, false, false
    ) {
        override fun afterActivityLaunched() {
            onWebView().forceJavascriptEnabled()
        }
    }

    @Test
    fun typeText_SubmitForm() {
        val webFormIntent = Intent()
        mActivity.launchActivity(webFormIntent)

        onWebView()
            .withElement(findElement(Locator.ID, "text_input"))
            .perform(clearElement())
            .perform(webKeys("TAU"))
        onWebView()
            .withElement(findElement(Locator.ID, "submitBtn"))
            .perform(webClick())
        onWebView()
            .withElement(findElement(Locator.ID, "response"))
            .check(webMatches(getText(), containsString("TAU")))
    }
}