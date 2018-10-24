package com.zmunm.narvcorp.sample.manager.view

import android.content.Intent
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.zmunm.narvcorp.sample.api.ID
import com.zmunm.narvcorp.sample.manager.BuildConfig
import com.zmunm.narvcorp.sample.manager.ManagerApp
import com.zmunm.narvcorp.sample.manager.ManagerAppModule
import com.zmunm.narvcorp.sample.manager.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ManagerApp::class,constants = BuildConfig::class)
class LoginActivityRoboTest {
    private val activity by lazy { Robolectric.setupActivity(LoginActivity::class.java)}
    private val pref by lazy { ManagerAppModule.providePreference(
            RuntimeEnvironment.application as? ManagerApp ?:throw TypeCastException()
    )}

    @Test
    fun login_Success() {
        activity.findViewById<EditText>(R.id.user_id).setText("testId")
        activity.findViewById<EditText>(R.id.user_pwd).setText("testPw")
        activity.findViewById<CheckBox>(R.id.auto_login_checkbox).isChecked = true
        activity.findViewById<Button>(R.id.btnLogin).performClick()
        Thread{Thread.sleep(2000)
            val actual = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity
            println(actual.component.className)
            assertEquals(
                    Intent(activity, MainActivity::class.java).component,
                    actual.component
            )
            assertEquals(pref.getString(ID,""),"testId")
            assertTrue(pref.getBoolean("isAuto",false))
        }.run()
    }

    @Test
    fun login_Fail() {
        activity.findViewById<EditText>(R.id.user_id).setText("testId")
        activity.findViewById<EditText>(R.id.user_pwd).setText("testFail")
        activity.findViewById<CheckBox>(R.id.auto_login_checkbox).isChecked = true
        activity.findViewById<Button>(R.id.btnLogin).performClick()
        Thread{Thread.sleep(2000)
            assertTrue(Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity == null)
            assertEquals(pref.getString(ID,""),"")
            assertFalse(pref.getBoolean("isAuto",false))
        }.run()
    }
}
