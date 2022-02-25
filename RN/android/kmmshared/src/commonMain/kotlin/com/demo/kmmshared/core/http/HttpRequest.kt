package com.demo.kmmshared.core.http

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.utils.*
import io.ktor.http.*

class HttpRequest(
    private val _url: String,
    private val _method: HttpMethod,
    private val _timeout: Long?,
    private var _retry: Int?,
    private var _body: Any?,
    private var _headers: Headers
) {

    private val httpClient: HttpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = _timeout ?: 25000L
        }

        defaultRequest {
            url(_url)
            method = _method
            body = _body ?: EmptyContent
            headers {
                append("Content-Type", "application/json")
                appendAll(_headers)
            }
        }
    }

    private constructor(
        builder: Builder
    ) : this(
        _url = builder.url,
        _body = builder.body,
        _retry = builder.retry,
        _method = builder.method,
        _timeout = builder.timeout,
        _headers = builder.headersBuilder.build(),
    )

    class Builder(val url: String) {
        var method: HttpMethod = HttpMethod.Get
        var timeout: Long? = null
        var retry: Int? = 0
        var body: Any? = null
        var headersBuilder: HeadersBuilder = HeadersBuilder()


        fun build() = HttpRequest(this)

        fun appendHeader(key: String, value: String): Builder {
            headersBuilder.append(key, value)
            return this
        }
    }

    suspend fun request(): HttpResponse? {
        var response: HttpResponse? = this.httpClient.request() {

        }

        if (!isSuccessfully(response) && (this._retry ?: 0) > 0) {
            this._retry = this._retry?.minus(1)
//            println("@@ retry for the $_retry time(s)")
            response = this.request()!!
        }

        return response
    }

    private fun isSuccessfully(response: HttpResponse?): Boolean {
        return response?.status?.value in 200..299
    }
}