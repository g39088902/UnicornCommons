package org.unicorn.whiteboard.common.http

import okhttp3.OkHttpClient
import org.unicorn.whiteboard.common.http.Constant.BASE_URL
import org.unicorn.whiteboard.common.http.Constant.ODS_URL
import org.unicorn.whiteboard.common.http.interceptor.CommonInterceptor
import org.unicorn.whiteboard.common.http.service.OdsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 可变参数的的传递需要添加"*"
/**
 * 使用接口请求方式
 */
object ApiFactory {
    var token = ""
    private val client: OkHttpClient by lazy {

        OkHttpClient.Builder()
            // .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(CommonInterceptor())
            .sslSocketFactory(
                SSLSocketClient.getSSLSocketFactory(),
                SSLSocketClient.getX509TrustManager()
            )
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun retrofitClient(url: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        val url = when (service) {
            // is ApiService -> BASE_URL
            is OdsService -> ODS_URL
            else -> BASE_URL
        }
        return create(url, service)
    }


    fun <T> create(url: String, service: Class<T>): T {
        return retrofitClient(url).create(service)
    }
}
