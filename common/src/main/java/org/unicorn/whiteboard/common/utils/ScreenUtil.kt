package org.unicorn.whiteboard.common.utils

import android.content.Context


object ScreenUtil {

    /*
   * 不包含状态栏
   * */
    fun getDisplayWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    /*
    * 不包含状态栏
    * */
    fun getDisplayHeight(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.heightPixels
    }

    @JvmStatic
    fun getStatusBarHeight(context: Context?): Int {
        var height = 0
        context?.let {
            val resourceId = it.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                height = it.resources.getDimensionPixelSize(resourceId)
            }
        }
        return height
    }
}