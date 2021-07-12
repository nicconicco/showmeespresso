package com.nicco.androidespressocodes.image

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nicco.androidespressocodes.R
import com.nicco.androidespressocodes.image.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class ImageViewerActivityTest {
    private val KEY_IMAGE_DATA: String = "KEY_IMAGE_DATA"

    @Rule
    @JvmField
    var mIntentsRule = IntentsTestRule(
        ImageViewerActivity::class.java
    )

    @Before
    fun stubCameraIntent() {
        val result: Instrumentation.ActivityResult = createImageCaptureActivityResultStub()

        // Stub the Intent.
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(result)
    }

    @Test
    fun takePhoto_drawableIsApplied() {
        // Check that the ImageView doesn't have a drawable applied.
        onView(withId(R.id.imageView)).check(matches(not(hasDrawable())))

        // Click on the button that will trigger the stubbed intent.
        onView(withId(R.id.button_take_photo)).perform(click())

        // With no user interaction, the ImageView will have a drawable.
        onView(withId(R.id.imageView)).check(matches(hasDrawable()))
    }

    private fun createImageCaptureActivityResultStub(): Instrumentation.ActivityResult {
        // Put the drawable in a bundle.
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                mIntentsRule.activity.resources, R.drawable.ic_launcher_background
            )
        )

        // Create the Intent that will include the bundle.
        val resultData = Intent()
        resultData.putExtras(bundle)

        // Create the ActivityResult with the Intent.
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }
}