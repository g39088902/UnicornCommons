package org.unicorn.whiteboard.common

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import org.unicorn.whiteboard.common.crash.ActivityManager
import org.unicorn.whiteboard.common.crash.CrashUtil
import org.unicorn.whiteboard.common.flowevent.FlowEventInitializer
import kotlin.properties.Delegates

open class CommonApplication : Application() {
    companion object {
        private val TAG = "CommonApplication"

        var context: Context by Delegates.notNull() //委托
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

        MMKV.initialize(this)

        ARouter.init(this)

        FlowEventInitializer.init(this)

        //活动管理
        ActivityManager.instance.init(this)

       CrashUtil.instance.init(this)


    }


    override fun onCreate() {
        super.onCreate()
        context = this
    }
}