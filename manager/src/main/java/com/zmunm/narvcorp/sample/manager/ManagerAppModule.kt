package com.zmunm.narvcorp.sample.manager

import android.app.Activity
import android.content.SharedPreferences
import com.zmunm.narvcorp.sample.api.AdminApi
import com.zmunm.narvcorp.sample.define.StaticObj
import com.zmunm.narvcorp.sample.model.PermissionModel
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object ManagerAppModule{
    @Provides
    @Reusable
    @JvmStatic
    fun providePreference(app: ManagerApp):SharedPreferences =
            app.getSharedPreferences(StaticObj.SHARED_PREF, Activity.MODE_PRIVATE)

    @Provides
    @Reusable
    @JvmStatic
    fun providePermission(): PermissionModel = PermissionModel()

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface() = Retrofit
            .Builder()
            .baseUrl(StaticObj.SV_DOMAIN)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideAdminApi(retrofit: Retrofit) = retrofit.create(AdminApi::class.java)
}
