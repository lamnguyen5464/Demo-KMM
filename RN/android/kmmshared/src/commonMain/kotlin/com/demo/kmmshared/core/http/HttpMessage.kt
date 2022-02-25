package com.demo.kmmshared.core.http

import com.demo.kmmshared.core.http.interceptor.HttpInterceptor
import com.demo.kmmshared.core.http.model.MHttpRequest
import com.demo.kmmshared.core.http.model.MHttpResponse

interface HttpMessage {
    fun addInterceptors(interceptors: List<HttpInterceptor>)
    fun setRequest(request: MHttpRequest)
    fun send(): MHttpResponse
}