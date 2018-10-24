package com.zmunm.narvcorp.sample.manager.viewmodel

import android.content.SharedPreferences
import com.zmunm.narvcorp.sample.api.AdminApi
import com.zmunm.narvcorp.sample.api.ID
import com.zmunm.narvcorp.sample.define.StaticFunc.sha256encode
import com.zmunm.narvcorp.sample.define.StaticObj
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule{
    @Provides
    fun provideViewModel(
            preferences: SharedPreferences,
            adminApi: AdminApi
    ) = LoginActivityViewModel(
            preferences,
           adminApi
    )
}

class LoginActivityViewModel(
        private val pref: SharedPreferences,
        private val adminApi: AdminApi
): StaticObj.ViewModel{
    var isAuto :Boolean = false
        set(value) {
            pref.edit().putBoolean("isAuto",value).apply()
            field = value
        }
    fun login(id : String, pw: String) = adminApi.select(id,sha256encode(pw))

    fun saveId(id:String) = pref.edit().putString(ID,id).apply()
}
