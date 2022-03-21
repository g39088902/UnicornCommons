package org.unicorn.whiteboard.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.bugsnag.android.Bugsnag

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bugsnag.start(this)
        ARouter.getInstance().inject(this)
        initPermission()
        initView()
    }

    private fun initPermission() {

    }


    abstract fun initView()



}