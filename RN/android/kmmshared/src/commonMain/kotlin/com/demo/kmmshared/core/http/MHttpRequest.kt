package com.demo.kmmshared.core.http

import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

class MHttpRequest private constructor(
    private val requestConfig: HttpRequestBuilder
) {
    private var _retry: Int = 0

    class Builder(private val url: String, val method: HttpMethod = HttpMethod.Get) {
        var retry: Int? = 0
        var body: Any? = null
        var timeout: Long? = null
        var needToEncryptBody: Boolean = false
        var headersBuilder: HeadersBuilder = HeadersBuilder()


        fun build(): MHttpRequest {
            val ktorRequestBuilder = HttpRequestBuilder()
            ktorRequestBuilder.url(url)
            ktorRequestBuilder.method = method
            ktorRequestBuilder.timeout {
                requestTimeoutMillis = timeout ?: 25000L
            }
            ktorRequestBuilder.headers {
                append("Content-Type", "application/json")
                appendAll(headersBuilder)
            }
            if (method != HttpMethod.Get && body != null) {
                ktorRequestBuilder.body = body as Any
            }
            return MHttpRequest(ktorRequestBuilder)
        }


        fun appendHeader(key: String, value: String): Builder {
            headersBuilder.append(key, value)
            return this
        }
    }

    fun getConfig(): HttpRequestBuilder {
        return this.requestConfig
    }
}