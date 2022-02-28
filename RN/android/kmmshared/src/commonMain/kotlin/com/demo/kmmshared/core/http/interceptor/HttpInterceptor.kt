package com.demo.kmmshared.core.http.interceptor

import com.demo.kmmshared.core.http.message.HttpBaseMessage
import com.demo.kmmshared.core.http.message.HttpMessage


interface HttpInterceptor {
    fun onWillSendRequest(httpMessage: HttpBaseMessage)
    fun onDidReceiveResponse(httpMessage: HttpBaseMessage)
}