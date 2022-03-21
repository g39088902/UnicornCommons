package org.unicorn.whiteboard.common.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.unicorn.whiteboard.common.CommonApplication.Companion.context

object Tips {
    var lastDisplayString = ""
    var lastDisplayTime = 0L

    fun toast(str: String) {
        if (str == lastDisplayString && System.currentTimeMillis() < lastDisplayTime) return
        lastDisplayString = str
        lastDisplayTime = System.currentTimeMillis() + 4000
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    /* fun toast(context:  Context, str: String) {
         if (str == lastDisplayString && System.currentTimeMillis() < lastDisplayTime) return
         lastDisplayString = str
         lastDisplayTime = System.currentTimeMillis() + 4000
          Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
     }
 */

}


val mainHandler by lazy {
    Handler(Looper.getMainLooper())
}


suspend fun Activity.toast(msg: String) {
    withContext(Dispatchers.Main) {
        toastPrivate(msg)
    }
}

suspend fun Activity.toastNetError() {
    withContext(Dispatchers.Main) {
        toastPrivate("网络连接已断开")
    }
}

suspend fun Fragment.toast(msg: String) {
    withContext(Dispatchers.Main) {
        requireContext().toastPrivate(msg)
    }
}

suspend fun Context.toast(msg: String) {
    withContext(Dispatchers.Main) {
        toastPrivate(msg)
    }
}

private fun Context.toastPrivate(msg: String) {
    if (msg == Tips.lastDisplayString && System.currentTimeMillis() < Tips.lastDisplayTime) return
    Tips.lastDisplayString = msg
    Tips.lastDisplayTime = System.currentTimeMillis() + 4000
    Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
}