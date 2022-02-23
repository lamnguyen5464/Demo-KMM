package com.demo.kmmshared.core.feature

import com.demo.kmmshared.core.crypto.EncryptAES
import com.demo.kmmshared.model.FeatureResponse
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json


class FeatureApi {

    companion object {
        const val URL_GET_FEATURES = "https://miniapp.dev.mservice.io/rigver-appversion/v1/features?last_update=0"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllFeatures(): FeatureResponse {
        val encrypt =  EncryptAES()

        val res = encrypt.encryptDecrypt("test string", "test key", "")
        println("@@ $res")
        return httpClient.get(URL_GET_FEATURES)
    }
}