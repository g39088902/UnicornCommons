package org.unicorn.whiteboard.common.ktx

import android.content.res.Resources

object IntUtil {
    inline val Int.dp:Int get() {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }
}