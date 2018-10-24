package com.zmunm.narvcorp.sample.manager.viewmodel

import android.content.SharedPreferences
import com.zmunm.narvcorp.sample.define.BaseActivityModule
import com.zmunm.narvcorp.sample.define.StaticObj
import com.zmunm.narvcorp.sample.api.AdminApi
import com.zmunm.narvcorp.sample.api.ID
import com.zmunm.narvcorp.sample.manager.view.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule: BaseActivityModule<MainActivity>() {
    @Provides
    fun provideViewModel(
            preferences: SharedPreferences,
            adminApi: AdminApi
    ) = MainActivityViewModel(
            preferences,
            adminApi
    )
}

class MainActivityViewModel(
        private val pref: SharedPreferences,
        private val adminApi: AdminApi
) : StaticObj.ViewModel {
    private val id = pref.getString(ID, "")
}
