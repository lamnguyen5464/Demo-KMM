package com.demo.kmm

import com.demo.kmm.model.CatFact
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class ApiRequest {
    companion object {
        private const val URL = "https://catfact.ninja/fact"
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

    @Throws(Exception::class) suspend fun getCatFact(): CatFact {
        return httpClient.get(URL)
    }
}