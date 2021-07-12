package com.nicco.androidespressocodes.utils

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.nicco.androidespressocodes.login.AnotherActivity
import org.hamcrest.Matcher

class RobotUtils {

    fun text(text: Int): RobotUtils {
        onView(withText(text)).check(matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun text(text: String): RobotUtils {
        onView(withText(text)).check(matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun isDisplayed(vararg viewsIds: Int): RobotUtils {
        for(id in viewsIds) {
            onView(withId(id)).check(matches(ViewMatchers.isDisplayed()))
        }
        return this
    }

    fun withHint(viewId : Int, hintId : Int): RobotUtils {
        onView(withId(viewId)).check(matches(withHint(hintId)))
        return this
    }

    fun withText(viewId : Int, textId : Int): RobotUtils {
        onView(withId(viewId)).check(matches(withText(textId)))
        return this
    }

    fun withHints(vararg pairs: Pair<Int, Int>): RobotUtils {
        for (pair in pairs) {
            onView(withId(pair.first)).check(matches(withHint(pair.second)))
        }
        return this
    }

    fun click(viewId: Int): RobotUtils {
        onView(withId(viewId)).perform(click())
        return this
    }

    fun checkIfGoToAnotherActivity(): RobotUtils {
        Intents.intended(IntentMatchers.hasComponent(AnotherActivity::class.java.name))
        return this
    }

    fun dialog(title: Int, msg: Int) {
        onView(withText(title)).inRoot(isDialog()).check(matches(ViewMatchers.isDisplayed()))
        onView(withText(msg)).inRoot(isDialog()).check(matches(ViewMatchers.isDisplayed()))
    }

    fun typeText(viewId: Int, text: String, closeKeyboard: Boolean = true) : RobotUtils {
        if(closeKeyboard) {
            onView(withId(viewId)).perform(click()).perform(typeText(text))
        } else {
            onView(withId(viewId)).perform(click()).perform(typeText(text), closeSoftKeyboard())
        }
        return this
    }

    fun wait(millis: Long): RobotUtils {
        onView(isRoot()).perform(waitForUtils(millis))
        return this
    }

    fun back(): RobotUtils {
        Espresso.pressBack()
        return this
    }

    private fun waitForUtils(millis: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}