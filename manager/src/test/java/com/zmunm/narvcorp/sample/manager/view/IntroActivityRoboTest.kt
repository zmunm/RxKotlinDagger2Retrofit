package com.zmunm.narvcorp.sample.manager.view

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.RuntimeEnvironment
import android.content.Intent
import com.zmunm.narvcorp.sample.manager.BuildConfig
import com.zmunm.narvcorp.sample.manager.ManagerApp
import com.zmunm.narvcorp.sample.manager.ManagerAppModule
import org.junit.Assert.assertEquals
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
@Config(application = ManagerApp::class,constants = BuildConfig::class)
class IntroActivityRoboTest {
    private val activityController by lazy { Robolectric.buildActivity(IntroActivity::class.java)}
    private val pref by lazy { ManagerAppModule.providePreference(
            RuntimeEnvironment.application as? ManagerApp ?:throw TypeCastException()
    )}

    @Test
    fun init_login() {
        val activity = activityController.create().get()
        Thread { Thread.sleep(3500)
            val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
            println(actual.component.className)
            assertEquals(Intent(activity, LoginActivity::class.java).component, actual.component)
        }.run()
    }

    @Test
    fun init_autoLogin() {
        pref.edit().putBoolean("isAuto",true).apply()
        val activity = activityController.create().get()
        Thread { Thread.sleep(3500)
            val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
            println(actual.component.className)
            assertEquals(Intent(activity, MainActivity::class.java).component, actual.component)
        }.run()
    }
}
