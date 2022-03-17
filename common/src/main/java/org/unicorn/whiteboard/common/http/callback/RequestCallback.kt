package org.unicorn.whiteboard.common.http.callback

import org.unicorn.whiteboard.common.http.model.ResponseError
import org.unicorn.whiteboard.common.http.model.ResponseSuccess

interface RequestCallback<T> {
    fun onSuccess(result: ResponseSuccess<T>)
    fun onError(error: ResponseError)
}
