package org.unicorn.whiteboard.common.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import java.lang.Exception
import kotlin.system.exitProcess

/*
* 通过反射的方式获取application
* 主要解决多module开发无法下沉到Base中
*
* */
object AppGlobals {
    var application: Application? = null

    @SuppressLint("PrivateApi")
    fun get(): Application? {
        if (application == null) {
            try {
                application = Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null) as Application
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return application
    }


}

