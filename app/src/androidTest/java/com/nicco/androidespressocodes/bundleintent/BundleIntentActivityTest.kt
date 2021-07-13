package com.nicco.androidespressocodes.bundleintent

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nicco.androidespressocodes.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * rodar testes via gradlew
 * ./gradlew connectedAndroidTest
 *
 * Desativar animacoes do emulador
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class BundleIntentActivityTest {

//    @Rule
//    @JvmField
//    var mActivityTestRule = ActivityTestRule(BundleIntentActivity::class.java)

//    lateinit var activityScenario: ActivityScenario<BundleIntentActivity>

//    @After
//    fun tearDown() {
//        activityScenario.close()
//    }

    @Test
    fun whenCompleteField_shouldVerifyBundleHasPassedToTheNextScreen(){
        ActivityScenario.launch(BundleIntentActivity::class.java)

        onView(withId(R.id.login)).perform(typeText("Nicco"), closeSoftKeyboard())
        onView(withId(R.id.email)).perform(typeText("nicolaugalves@gmail.com"), closeSoftKeyboard())

        onView(withId(R.id.btnLogin)).perform(click())

        onView(withId(R.id.loginBundle)).check(matches(isDisplayed()))
        onView(withId(R.id.loginBundle)).check(matches(withText("Hola!, Nicco")))
        onView(withId(R.id.emailBundle)).check(matches(isDisplayed()))
        onView(withId(R.id.emailBundle)).check(matches(withText("Seu email: nicolaugalves@gmail.com")))
    }
}