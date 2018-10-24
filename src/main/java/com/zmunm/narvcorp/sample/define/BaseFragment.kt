package com.zmunm.narvcorp.sample.define

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment<T: StaticObj.ViewModel> : Fragment() {
    private val disposable by lazy { CompositeDisposable() }
    @Inject
    protected lateinit var viewModel: T

    public override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
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