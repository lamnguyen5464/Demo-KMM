package com.demo.kmmshared.core.http.interceptor


interface HttpInterceptor {
    fun onWillSendRequest()
    fun onDidRecieveResponse()
}