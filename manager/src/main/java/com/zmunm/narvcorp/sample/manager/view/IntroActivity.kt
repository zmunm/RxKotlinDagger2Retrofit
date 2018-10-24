package com.zmunm.narvcorp.sample.manager.view

import android.content.Intent
import android.os.Bundle
import com.zmunm.narvcorp.sample.define.BaseActivity
import com.zmunm.narvcorp.sample.manager.R
import com.zmunm.narvcorp.sample.manager.viewmodel.IntroActivityViewModel
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class IntroActivity : BaseActivity<IntroActivityViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        permissionCheck()
    }

    private fun permissionCheck() {
        viewModel.permissionCheck(this)
                ?.subscribe({
                    init()
                },{
                    finish()
                })
                ?.done()
                ?:init()
    }

    private fun init() {
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribe{
                    startActivity(Intent(this@IntroActivity,
                            if(viewModel.isAuto){ MainActivity::class.java}
                            else {
                                LoginActivity::class.java}
                    )).run {
                        finish()
                    }
                }
                .done()
    }
}
