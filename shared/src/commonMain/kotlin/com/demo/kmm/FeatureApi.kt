package com.demo.kmm

import com.demo.kmm.model.CatFact
import com.demo.kmm.model.FeatureResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class FeatureApi {

    companion object {
        const val URL_GET_FEATURES = "https://miniapp.dev.mservice.io/rigver-appversion/v1/features?last_update=0"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllFeatures(): FeatureResponse {
        return httpClient.get(URL_GET_FEATURES)
    }
}