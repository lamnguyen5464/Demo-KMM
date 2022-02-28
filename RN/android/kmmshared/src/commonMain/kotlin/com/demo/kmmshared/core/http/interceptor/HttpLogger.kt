package com.demo.kmmshared.core.http.interceptor

import com.demo.kmmshared.core.http.message.HttpBaseMessage
import com.demo.kmmshared.core.http.message.HttpMessage

class HttpLogger: HttpInterceptor {
    override fun onWillSendRequest(httpMessage: HttpBaseMessage) {
        println("@@ call from HttpLogger before send")
    }

    override fun onDidReceiveResponse(httpMessage: HttpBaseMessage) {
    }
}