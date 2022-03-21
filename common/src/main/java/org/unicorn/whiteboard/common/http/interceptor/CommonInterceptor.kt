package org.unicorn.whiteboard.common.http.interceptor

import android.util.Log
import com.tencent.mmkv.MMKV
import okhttp3.Interceptor
import okhttp3.Response

class CommonInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = MMKV.mmkvWithID("auth").decodeString("authToken", "")
        //Log.i("token", token ?: "")
        val requestBuilder = original.newBuilder()
                .addHeader("token", token)
                .method(original.method(), original.body())

        val request = requestBuilder.build()
        return chain.proceed(request)

    }
}