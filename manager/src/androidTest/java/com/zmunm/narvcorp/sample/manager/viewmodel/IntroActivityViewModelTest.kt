package com.zmunm.narvcorp.sample.manager.viewmodel

import android.support.test.InstrumentationRegistry
import com.zmunm.narvcorp.sample.manager.ManagerApp
import com.zmunm.narvcorp.sample.manager.ManagerAppModule
import com.zmunm.narvcorp.sample.manager.viewmodel.IntroActivityModule
import com.zmunm.narvcorp.sample.manager.viewmodel.IntroActivityViewModel
import org.junit.Test

class IntroActivityViewModelTest {
    private val viewModel: IntroActivityViewModel by lazy{
        val app = InstrumentationRegistry.getTargetContext().applicationContext as? ManagerApp ?:throw TypeCastException()
        IntroActivityModule().provideViewModel(
                ManagerAppModule.providePreference(app),
                ManagerAppModule.providePermission(),
                ManagerAppModule.provideAdminApi(ManagerAppModule.provideRetrofitInterface())
        )
    }

    @Test
    fun isAuto() {
        println(viewModel.isAuto)
    }
}
