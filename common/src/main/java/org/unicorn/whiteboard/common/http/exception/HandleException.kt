package org.unicorn.whiteboard.common.http.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.unicorn.whiteboard.common.utils.Tips.toast
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException

object HandleException {

    fun handleException(t: Throwable): ResultException {
        val ex: ResultException
        when (t) {
            is ResultException -> ex = t
            is HttpException -> ex = when (t.code()) {
                ApiResultCode.UNAUTHORIZED,
                ApiResultCode.FORBIDDEN, //权限错误，需要实现
                ApiResultCode.NOT_FOUND -> ResultException(t.code().toString(), "网络错误")
                ApiResultCode.REQUEST_TIMEOUT,
                ApiResultCode.GATEWAY_TIMEOUT -> ResultException(t.code().toString(), "网络连接超时")
                ApiResultCode.INTERNAL_SERVER_ERROR,
                ApiResultCode.BAD_GATEWAY,
                ApiResultCode.SERVICE_UNAVAILABLE -> ResultException(t.code().toString(), "服务器错误")
                else -> ResultException(t.code().toString(), "网络错误")
            }
            is JsonParseException, is JSONException, is ParseException -> ex = ResultException("0", "解析错误")
            is SocketException -> ex = ResultException(ApiResultCode.REQUEST_TIMEOUT.toString(), "网络连接错误，请重试")
            is SocketTimeoutException -> ex = ResultException(ApiResultCode.REQUEST_TIMEOUT.toString(), "网络连接超时")
            is SSLHandshakeException -> ex = ResultException("0", "证书验证失败")
            is UnknownHostException -> ex = ResultException("0", "网络错误，请切换网络重试")
            is UnknownServiceException -> ex = ResultException("0", "网络错误，请切换网络重试")
            is NumberFormatException -> ex = ResultException("0", "数字格式化异常")
            else -> {
                ex = ResultException("0", "未知错误")
            }
        }
        CoroutineScope(Dispatchers.Main).launch{
            toast(ex.msg)
        }

        return ex
    }
}