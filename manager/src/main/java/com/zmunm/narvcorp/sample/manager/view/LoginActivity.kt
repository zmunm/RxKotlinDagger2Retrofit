package com.zmunm.narvcorp.sample.manager.view

import android.content.Intent
import android.os.Bundle
import com.zmunm.narvcorp.sample.define.BaseActivity
import com.zmunm.narvcorp.sample.manager.viewmodel.LoginActivityViewModel
import com.jakewharton.rxbinding2.view.clicks
import com.zmunm.narvcorp.sample.manager.R
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.clicks()
                .flatMap { viewModel.login(user_id.text.toString(),user_pwd.text.toString()) }
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewModel.saveId(it.id)
                    viewModel.isAuto = auto_login_checkbox.isChecked
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                },{
                    it.printStackTrace()
                })
                .done()
    }
}
