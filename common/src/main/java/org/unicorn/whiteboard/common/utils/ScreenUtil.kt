package org.unicorn.whiteboard.common.utils

import android.content.Context

object ScreenUtil {

    /**
     * 不包含状态
     */
    fun getDisplayWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    /**
     * 状态栏高度
     */
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