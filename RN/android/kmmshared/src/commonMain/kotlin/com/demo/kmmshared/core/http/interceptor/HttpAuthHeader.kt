package com.demo.kmmshared.core.http.interceptor

import com.demo.kmmshared.core.http.message.HttpBaseMessage
import com.demo.kmmshared.core.http.message.HttpMessage

class HttpAuthHeader: HttpInterceptor {
    override fun onWillSendRequest(httpMessage: HttpBaseMessage) {
        println("@@ call from HttpAuthHeader before send")
    }

    override fun onDidReceiveResponse(httpMessage: HttpMessage) {
    }
}