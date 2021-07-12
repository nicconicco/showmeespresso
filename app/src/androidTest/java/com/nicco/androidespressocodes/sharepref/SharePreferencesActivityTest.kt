package com.nicco.androidespressocodes.sharepref

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences.Editor
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.nicco.androidespressocodes.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class SharePreferencesActivityTest {

    @Rule
    @JvmField
    var activityTestRule: IntentsTestRule<SharePreferencesActivity> = object : IntentsTestRule<SharePreferencesActivity>(
        SharePreferencesActivity::class.java
    ) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            val context: Context = InstrumentationRegistry.getInstrumentation()
                .targetContext
            val editor: Editor = context
                .getSharedPreferences(
                    context.packageName,
                    Activity.MODE_PRIVATE
                ).edit()
            editor.putBoolean("STATE_PREF", true)
            editor.commit()
        }
    }

    @Test
    fun whenClickTwoTimesValueOfTextWillBeTrue(){
        onView(withId(R.id.btn)).perform(click(), click())
        onView(withId(R.id.sub_title)).check(matches(withText("true")));
    }

    @Test
    fun whenClickValueOfTextWillBeFalse(){
        onView(withId(R.id.btn)).perform(click())
        onView(withId(R.id.sub_title)).check(matches(withText("false")));
    }
}