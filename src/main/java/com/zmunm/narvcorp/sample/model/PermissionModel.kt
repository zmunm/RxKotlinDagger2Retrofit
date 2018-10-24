package com.zmunm.narvcorp.sample.model

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.zmunm.narvcorp.sample.R
import io.reactivex.subjects.BehaviorSubject

class PermissionModel{
    private val subject by lazy { BehaviorSubject.create<Unit>() }
    @RequiresApi(Build.VERSION_CODES.M)
    fun permissionCheck(activity: Activity,permissions:Array<String>) = subject
            .takeIf { Build.VERSION.SDK_INT >= Build.VERSION_CODES.M }
            ?.apply {
                fun check():Unit = permissions.firstOrNull {
                    activity.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
                }?.let {
                    TedPermission.with(activity)
                            .setPermissionListener(object :PermissionListener {
                                override fun onPermissionGranted() {
                                    onNext(Unit)
                                }

                                override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                                    AlertDialog.Builder(activity)
                                            .setMessage(activity.getString(R.string.content_permission_rejected))
                                            .setPositiveButton(R.string.btn_end) { _, _ ->
                                                onError(IllegalAccessException(permissions.joinToString(",")))
                                            }
                                            .setNegativeButton(activity.getString(R.string.btn_ok)) { _, _ ->
                                                check()
                                            }
                                            .show()

                                }
                            })
                            .setPermissions(*permissions)
                            .check()
                }?:let {
                    onNext(Unit)
                }
                check()
            }
}