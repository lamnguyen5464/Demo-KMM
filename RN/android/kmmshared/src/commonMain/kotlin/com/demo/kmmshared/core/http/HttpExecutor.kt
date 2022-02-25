package com.demo.kmmshared.core.http

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class HttpExecutor {
    companion object {
        private val httpClient: HttpClient = HttpClient {
            install(JsonFeature) {
                val json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                }
                serializer = KotlinxSerializer(json)
            }

            install(HttpTimeout) {
            }
        }
    }


    suspend fun execute(request: MHttpRequest): MHttpResponse {
        return httpClient.request(request.getConfig())
    }
}