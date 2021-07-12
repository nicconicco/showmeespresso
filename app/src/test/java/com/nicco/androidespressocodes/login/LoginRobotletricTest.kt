package com.nicco.androidespressocodes.login

import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nicco.androidespressocodes.R
import com.nicco.androidespressocodes.utils.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class LoginRobotletricTest {

    private val robot = Robot()

    @Before
    fun setup() {
        Intents.init()
        robot.setup()
    }

    @After
    fun down() {
        Intents.release()
    }

    /**
     * This is an option to do your tests
     */
    @Test
    fun `check if login works with robotUtils`() {
        ActivityScenario.launch(LoginActivity::class.java)
        RobotUtils()
            .typeText(R.id.etLogin, "login")
            .typeText(R.id.etSenha, "senha")
            .wait(1000)
            .click(R.id.btLogin)
            .checkIfGoToAnotherActivity()
    }

    /**
     * This is similar how we do in our work
     */
    @Test
    fun `working with lambdas pre configuresScenarios`() {
        // this is how we practice in our enviroment
        configureScenario {
            launch()
        } action {
            doLogin()
        } assert {
            validateScenarioAfterDidLogin()
        }
    }

    /**
     * this content was a copy and adapt of this url:
     * https://medium.com/gumtree-dev-team/android-bdd-with-robolectric-and-espresso-how-to-refactor-your-android-project-tests-d79a07870da0
     */
    @Test
    fun `Another option of how to do unit test with roboletric`() {
        RUN_UNIT_TEST(robot) {
            GIVEN { typeTextLogin() }
            GIVEN { typeTextSenha() }
            WHEN { loginSubmit() }
            THEN { validateScenarioAfterDidLogin() }
        }
    }

    private class Robot: BaseRobot() {
        fun typeTextSenha() {
            RobotUtils().typeText(R.id.etSenha, "senha")
        }

        fun typeTextLogin() {
            RobotUtils()
                .typeText(R.id.etLogin, "login")
        }

        fun loginSubmit() {
            RobotUtils().click(R.id.btLogin)
        }

        fun validateScenarioAfterDidLogin() {
            Intents.intended(IntentMatchers.hasComponent(AnotherActivity::class.java.name))
        }
    }
}