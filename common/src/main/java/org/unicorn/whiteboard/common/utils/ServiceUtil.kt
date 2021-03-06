package org.unicorn.whiteboard.common.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.Log

object ServiceUtil {

    fun isServiceWorking(activity: Activity, className: String): Boolean {
        val activityManager =
            activity.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var serviceList: List<ActivityManager.RunningServiceInfo> =
            activityManager.getRunningServices(30)

         if (serviceList.isEmpty()) {
            Log.i("isServiceWorking", "false")
            return false
        }
        serviceList.forEach { item ->
            if (item.service.className == className) {
                Log.i("isServiceWorking", "true")
                return true
            }
        }
        Log.i("isServiceWorking", "false")

        return false
    }
}