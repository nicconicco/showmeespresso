package com.nicco.androidespressocodes.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import com.nicco.androidespressocodes.AnotherActivity
import com.nicco.androidespressocodes.LoginActivity
import com.nicco.androidespressocodes.R


fun configureScenario(
    func: LoginActivityRobot.() -> Unit
) = LoginActivityRobot().apply(func)

class LoginActivityRobot {
    infix fun action(func: LoginActivityRobotAct.() -> Unit) = LoginActivityRobotAct().apply(func)
    fun launch() {
        ActivityScenario.launch(LoginActivity::class.java)
    }

    private fun typeText(viewId: Int, text: String) {
        onView(withId(viewId))
            .perform(ViewActions.click()).perform(ViewActions.typeText(text))
    }

    inner class LoginActivityRobotAct() {
        infix fun assert(func: LoginActivityRobotAssert.() -> Unit) =
            LoginActivityRobotAssert().apply(func)

        fun performClick(viewId: Int) {
            onView(withId(viewId)).perform(ViewActions.click())
        }

        fun doLogin() {
            typeText(R.id.etLogin, "login")
            typeText(R.id.etSenha, "senha")
            performClick(R.id.btLogin)
        }
    }

    inner class LoginActivityRobotAssert() {
        fun validateScenarioAfterDidLogin() {
            intended(hasComponent(AnotherActivity::class.java.name))
        }
    }
}