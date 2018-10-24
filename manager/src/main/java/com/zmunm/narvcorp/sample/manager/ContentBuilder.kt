package com.zmunm.narvcorp.sample.manager

import com.zmunm.narvcorp.sample.manager.view.IntroActivity
import com.zmunm.narvcorp.sample.manager.view.LoginActivity
import com.zmunm.narvcorp.sample.manager.view.MainActivity
import com.zmunm.narvcorp.sample.manager.viewmodel.IntroActivityModule
import com.zmunm.narvcorp.sample.manager.viewmodel.LoginActivityModule
import com.zmunm.narvcorp.sample.manager.viewmodel.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContentBuilder {
    @ContributesAndroidInjector(modules = [IntroActivityModule::class])
    abstract fun `IntroActivity`(): IntroActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun `MainActivity`(): MainActivity

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun `LoginActivity`(): LoginActivity
}
