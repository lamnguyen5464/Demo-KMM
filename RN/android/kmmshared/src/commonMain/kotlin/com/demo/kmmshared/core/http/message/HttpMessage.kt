package com.demo.kmmshared.core.http.message

import com.demo.kmmshared.core.http.interceptor.HttpEncryptor
import com.demo.kmmshared.core.http.interceptor.HttpInterceptor
import com.demo.kmmshared.core.http.interceptor.HttpLogger

class HttpMessage() : HttpBaseMessage() {
    override fun getInterceptors(): List<HttpInterceptor> {
        return listOf(
            HttpLogger(),
            HttpEncryptor(),
            // custom interceptor here
            object : HttpInterceptor {
                override fun onWillSendRequest(httpMessage: HttpBaseMessage) {
                }

                override fun onDidReceiveResponse(httpMessage: HttpMessage) {}
            })
    }
}