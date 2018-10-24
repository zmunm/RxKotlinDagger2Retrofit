package com.zmunm.narvcorp.sample.manager.viewmodel

import android.support.test.InstrumentationRegistry
import com.zmunm.narvcorp.sample.manager.ManagerApp
import com.zmunm.narvcorp.sample.manager.ManagerAppModule
import com.zmunm.narvcorp.sample.manager.viewmodel.LoginActivityViewModel
import org.junit.Test

class LoginActivityViewModelTest {
    private val viewModel: LoginActivityViewModel by lazy{
        val app = InstrumentationRegistry.getTargetContext().applicationContext as? ManagerApp ?:throw TypeCastException()
        LoginActivityViewModel(
                ManagerAppModule.providePreference(app),
                ManagerAppModule.provideAdminApi(ManagerAppModule.provideRetrofitInterface())
        )
    }

    @Test
    fun login() {
        viewModel.login("testId","testPw")
                .subscribe ({
                    assert(it.email=="test@test.tt")
                },{
                    it.printStackTrace()
                })
    }
}
