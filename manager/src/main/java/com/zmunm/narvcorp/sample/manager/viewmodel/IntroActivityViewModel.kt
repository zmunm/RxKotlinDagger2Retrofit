package com.zmunm.narvcorp.sample.manager.viewmodel

import android.annotation.TargetApi
import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import com.zmunm.narvcorp.sample.define.StaticObj
import com.zmunm.narvcorp.sample.model.PermissionModel
import com.zmunm.narvcorp.sample.api.AdminApi
import dagger.Module
import dagger.Provides

@Module
class IntroActivityModule{
    @Provides
    fun provideViewModel(
            preferences: SharedPreferences,
            permissionModel: PermissionModel,
            adminApi: AdminApi
    ) = IntroActivityViewModel(
            preferences,
            permissionModel,
            adminApi
    )
}

class IntroActivityViewModel(
        private val pref: SharedPreferences,
        private val permissionModel: PermissionModel,
        private val adminApi: AdminApi
): StaticObj.ViewModel{
    val isAuto = pref.getBoolean("isAuto",false)

    @TargetApi(Build.VERSION_CODES.M)
    fun permissionCheck(activity: Activity) = permissionModel.permissionCheck(activity, arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    ))
}
