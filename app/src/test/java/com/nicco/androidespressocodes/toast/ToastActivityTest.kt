package com.nicco.androidespressocodes.toast

import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nicco.androidespressocodes.R
import com.nicco.androidespressocodes.login.LoginActivity
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ToastActivityTest {

    @Test
    fun test(){
        ActivityScenario.launch(ToastActivity::class.java)

        onView(withId(R.id.buttonToast))
            .perform(click())

        assertThat(ShadowToast.getTextOfLatestToast().toString(), equalTo("Toast sendo mostrado"))
    }
}