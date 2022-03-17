package org.unicorn.whiteboard.common.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.unicorn.whiteboard.common.http.ApiFactory.token

class CommonInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("token", token)
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        return chain.proceed(request)

    }
}