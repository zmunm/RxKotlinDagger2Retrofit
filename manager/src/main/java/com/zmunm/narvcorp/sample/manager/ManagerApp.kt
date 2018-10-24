package com.zmunm.narvcorp.sample.manager

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ManagerApp : Application(),HasActivityInjector/*,HasServiceInjector, HasSupportFragmentInjector */{
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
   /* @Inject
    lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>*/

    override fun onCreate() {
        super.onCreate()
        DaggerManagerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
  /*  override fun serviceInjector() = serviceDispatchingAndroidInjector*/
  //  override fun supportFragmentInjector() = fragmentInjector
}
