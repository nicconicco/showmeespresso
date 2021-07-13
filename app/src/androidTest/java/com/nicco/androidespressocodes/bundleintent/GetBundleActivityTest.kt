package com.nicco.androidespressocodes.bundleintent

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nicco.androidespressocodes.R
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GetBundleActivityTest {

    /**
     * Old way
     *   @Rule
        @JvmField
         var mActivityRule: ActivityTestRule<BundleIntentActivity> = object : ActivityTestRule<BundleIntentActivity>(
        BundleIntentActivity::class.java
         ) {
        override fun getActivityIntent(): Intent {
            val targetContext: Context = InstrumentationRegistry.getInstrumentation()
            .targetContext
            val result = Intent(targetContext, BundleIntentActivity::class.java)
            result.putExtra("LOGIN", "Nicco")
            result.putExtra("EMAIL", "nicolaugalves@gmail.com")
            return result
            }
        }
     */

    lateinit var activityScenario: ActivityScenario<GetBundleActivity>

    @After
    fun tearDown() {
        activityScenario.close()
    }

    @Test
    fun check_if_bundle_intents_is_passed() {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), GetBundleActivity::class.java)
                .putExtra("LOGIN", "Nicco")
                .putExtra("EMAIL", "nicolaugalves@gmail.com")

        activityScenario = ActivityScenario.launch(intent)

        onView(withId(R.id.loginBundle))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.loginBundle))
            .check(ViewAssertions.matches(withText("Hola!, Nicco")))
        onView(withId(R.id.emailBundle))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.emailBundle))
            .check(ViewAssertions.matches(withText("Seu email: nicolaugalves@gmail.com")))
    }
}