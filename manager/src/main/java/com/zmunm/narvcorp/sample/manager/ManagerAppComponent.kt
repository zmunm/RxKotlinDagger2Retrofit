package com.zmunm.narvcorp.sample.manager

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ManagerAppModule::class,
    ContentBuilder::class
])
interface ManagerAppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance fun application(app: ManagerApp): Builder
        fun build(): ManagerAppComponent
    }

    fun inject(app: ManagerApp)
}
