package com.nicco.androidespressocodes.utils

import androidx.test.core.app.ActivityScenario
import com.nicco.androidespressocodes.login.LoginActivity

/**
 * Each test Robot in our application (UI test Robot or Unit test Robot) should extend BaseRobot
 */
open class BaseRobot {
    /**
     * Use the method to setup stuff in your Robot class before each test run
     */
    open fun setup() {
        ActivityScenario.launch(LoginActivity::class.java)
    }

    open fun tearsDown() {
        //no base implementation
    }
}