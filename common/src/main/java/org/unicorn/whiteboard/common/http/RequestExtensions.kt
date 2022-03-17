package org.unicorn.whiteboard.common.http

import android.util.Log
import kotlinx.coroutines.*
import org.unicorn.whiteboard.common.http.callback.RequestCallback
import org.unicorn.whiteboard.common.http.model.ResponseError
import org.unicorn.whiteboard.common.http.model.ResponseSuccess

fun <T> request(
    scope: CoroutineScope,
    callback: RequestCallback<T>? = null,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    handler: CoroutineExceptionHandler? = null,
    block: suspend CoroutineScope.() -> T
): Job = scope.launch(dispatcher) {
    try {
        val result = block()
        Log.i("block()", result.toString())
        withContext(Dispatchers.Main) {
            Log.i("block()--Dispatchers.Main", result.toString())

            callback?.onSuccess(ResponseSuccess(result))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        withContext(Dispatchers.Main) {
            handler?.handleException(this.coroutineContext, e)
            callback?.onError(ResponseError(e))
        }
    }
}
