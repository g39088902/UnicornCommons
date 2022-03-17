package org.unicorn.whiteboard.common.crash

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * 监听Activity生命周期管理
 */
class ActivityManager private constructor() {
    private val activityRefs = ArrayList<WeakReference<Activity>>()//记录activity
    private val frontBackCallback = ArrayList<FrontBackCallback>()
    private var activityStartCount = 0
    var mIsFront = true //当前是否处于前台


    /**
     * 构建单例
     */
    companion object {
        @JvmStatic
        val instance: ActivityManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityManager()
        }

     }


    /**
     * 初始化,注册Activity生命周期的回调
     */
    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(InnerActivityLifecycleCallback())
    }

    /**
     * activity状态的监听变化
     */
    private fun onFrontBackChanged(front: Boolean) {
        for (callback in frontBackCallback) {
            callback.onChanged(front)
        }
    }

    /**
     * 获取顶层activity的实例
     */
    val topActivity: Activity?
        get() {
            return if (activityRefs.size <= 0) null
            else {
                activityRefs[activityRefs.size - 1].get()
            }
        }

    fun addFrontBackCallback(frontBackCallback: FrontBackCallback) {
        this.frontBackCallback.add(frontBackCallback)

    }

    fun removeFrontBackCallback(frontBackCallback: FrontBackCallback) {
        this.frontBackCallback.remove(frontBackCallback)

    }


    /**
     * 监听Activity生命周期
     */
    inner class InnerActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activityRefs.add(WeakReference(activity))
        }

        override fun onActivityStarted(activity: Activity) {
            activityStartCount++
            if (!mIsFront && activityStartCount > 0) {
                mIsFront = true
                onFrontBackChanged(mIsFront)
            }
        }


        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {
            activityStartCount--

            if (activityStartCount <= 0 && mIsFront) {
                mIsFront = false
                onFrontBackChanged(mIsFront)
            }

        }


        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {

            for (activityRef in activityRefs) {
                if (activityRef.get() == activity) {
                    activityRefs.remove(activityRef)
                    break
                }

            }
        }


    }


    interface FrontBackCallback {
        fun onChanged(front: Boolean)
    }

}