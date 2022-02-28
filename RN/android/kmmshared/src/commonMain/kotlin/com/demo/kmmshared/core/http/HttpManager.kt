package com.demo.kmmshared.core.http

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class HttpManager {
    companion object {
        private var httpManagerInstance: HttpManager? = null
        fun getInstance(): HttpManager {
            if (httpManagerInstance == null) {
                httpManagerInstance = HttpManager()
            }
            return httpManagerInstance!!
        }
    }

    private val httpClient: HttpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }

        install(HttpTimeout) {}
    }


    suspend fun addRequest(request: MHttpRequest): MHttpResponse {
        // TODO: impl message queue here
        return httpClient.request(request.getConfig())
    }
}