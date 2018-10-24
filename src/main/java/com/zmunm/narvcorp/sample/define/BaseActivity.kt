package com.zmunm.narvcorp.sample.define

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseActivity<T: StaticObj.ViewModel> : FragmentActivity() {
    private val disposable by lazy { CompositeDisposable() }
    @Inject
    protected lateinit var viewModel: T

    public override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        disposable.dispose()
    }

    fun <T:Disposable> T.done(){
        disposable.add(this)
    }
}