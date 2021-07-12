package com.nicco.androidespressocodes.dialer

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.nicco.androidespressocodes.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * this is basically a copy to understand how it works
 * https://github.com/android/testing-samples/blob/master/ui/espresso/IntentsBasicSample/app/src/sharedTest/java/com/example/android/testing/espresso/IntentsBasicSample/DialerActivityTest.java
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class DialerActivityTest {
    private val phoneNumber = "123-345-6789"
    private val IntentPhoneNumber: Uri = Uri.parse("tel:$phoneNumber")

//    @Rule
//    @JvmField
//    var grantPermissionRule = GrantPermissionRule.grant("android.permission.CALL_PHONE")

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(
        DialerActivity::class.java
    )

    @Before
    fun stubAllExternalIntents() {
        Intents.init()
        intending(not(isInternal()))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }

    @After
    fun after(){
        Intents.release()
    }

    @Test
    fun typeNumber_ValidInput_InitiatesCall() {
        onView(withId(R.id.edit_text_caller_number)).perform(
            typeText(phoneNumber),
            closeSoftKeyboard()
        )

        onView(withId(R.id.button_call_number)).perform(click())

        intended(
            allOf(
                hasAction(Intent.ACTION_CALL),
                hasData(IntentPhoneNumber)
            )
        )
    }
}