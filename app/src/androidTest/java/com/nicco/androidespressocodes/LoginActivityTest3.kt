package com.nicco.androidespressocodes


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nicco.androidespressocodes.login.LoginActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest3 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivityTest3() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.etLogin),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("teste"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.etSenha),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("teste"), closeSoftKeyboard())

        val textView = onView(
            allOf(
                withId(R.id.tvTitle), withText("Espresso Exemplos"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Espresso Exemplos")))

        val textView2 = onView(
            allOf(
                withId(R.id.tvLogin), withText("Login"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Login")))

        val button = onView(
            allOf(
                withId(R.id.btLogin), withText("LOGIN"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withText("Hello"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Hello")))

        val textView4 = onView(
            allOf(
                withText("Hello"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Hello")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
