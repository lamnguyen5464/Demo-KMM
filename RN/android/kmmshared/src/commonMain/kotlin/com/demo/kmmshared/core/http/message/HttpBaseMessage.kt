package com.demo.kmmshared.core.http.message

import com.demo.kmmshared.core.http.HttpManager
import com.demo.kmmshared.core.http.MHttpRequest
import com.demo.kmmshared.core.http.MHttpResponse
import com.demo.kmmshared.core.http.interceptor.HttpInterceptor

abstract class HttpBaseMessage(
) {

    lateinit var request: MHttpRequest
    private val httpManager: HttpManager = HttpManager.getInstance()


    abstract fun getInterceptors(): List<HttpInterceptor>

    suspend fun send(): MHttpResponse {
        executeBeforeSend()
        val res = httpManager.addRequest(this.request)
        executeAfterReceive()
        return res
    }

    private fun executeBeforeSend() {
        val listInterceptors = getInterceptors()
        listInterceptors.forEach {
            it.onWillSendRequest(this)
        }
    }

    private fun executeAfterReceive() {
        val listInterceptors = getInterceptors()
        listInterceptors.forEach {
            it.onWillSendRequest(this)
        }
    }

}