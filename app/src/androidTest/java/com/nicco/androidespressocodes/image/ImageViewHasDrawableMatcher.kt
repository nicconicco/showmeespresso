package com.nicco.androidespressocodes.image

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description


object ImageViewHasDrawableMatcher {
    fun hasDrawable(): BoundedMatcher<View?, ImageView> {
        return object : BoundedMatcher<View?, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has drawable")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                return imageView.drawable != null
            }
        }
    }
}