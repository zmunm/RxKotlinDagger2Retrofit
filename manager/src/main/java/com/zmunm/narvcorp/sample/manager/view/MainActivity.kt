package com.zmunm.narvcorp.sample.manager.view

import android.os.Bundle
import android.widget.Toast
import com.zmunm.narvcorp.sample.define.BaseActivity
import com.zmunm.narvcorp.sample.manager.R
import com.zmunm.narvcorp.sample.manager.viewmodel.MainActivityViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class MainActivity : BaseActivity<MainActivityViewModel>() {
    private val backButtonSubject: Subject<Long> = BehaviorSubject.createDefault(0L).toSerialized()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*백버튼 두번 누르면 종료되는 함수*/
        backButtonSubject.toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(2, 1) // List<Long>
                /*it : 누른 시간*/
                .map { it[0] to it[1] }
                .subscribe {
                    /*3초 이하면 종료시킨다.*/
                    if (it.second - it.first < 3) {
                        finish()
                    } else
                        Toast.makeText(
                                baseContext,
                                "뒤로가기 버튼을 한번 더 누르면 종료됩니다.",
                                Toast.LENGTH_SHORT
                        ).show()
                }
                .done()
    }

    /*백버튼 리스너*/
    override fun onBackPressed() {
        backButtonSubject.onNext(System.currentTimeMillis() / 1000)
    }
}

