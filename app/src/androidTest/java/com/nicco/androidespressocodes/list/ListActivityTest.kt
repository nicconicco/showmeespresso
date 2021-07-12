package com.nicco.androidespressocodes.list


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nicco.androidespressocodes.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Espresso dont work very well with animation
 * https://stackoverflow.com/questions/44005338/android-espresso-performexception
 *
 * So if you want to check something it is not visible, scroll to to help, remeber to add
 * androidx.test.espresso.contrib.RecyclerViewActions to help
 *
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    private val itemText = 40
    private val displayedText = "This is element #$itemText"

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ListActivity::class.java)

    @Test
    fun listActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerView),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.textView), withText("This is element #0"),
                withParent(withParent(withId(R.id.recyclerView))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("This is element #0")))

        val textView2 = onView(
            allOf(
                withId(R.id.textView), withText("This is element #5"),
                withParent(withParent(withId(R.id.recyclerView))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("This is element #5")))
    }

    @Test
    fun scrollToItem_checkItsText() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText(displayedText))
            )
        )

        onView(withId(R.id.recyclerView)) .perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText(displayedText))
            ), click()
        )

        onView(withText(displayedText))
            .check(matches(isDisplayed()))
    }
}
