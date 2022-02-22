package com.demo.kmm

import com.demo.kmm.model.CatFact
import com.demo.kmm.model.CatFactEntity
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.query
import kotlinx.serialization.json.Json

class CatFactApi {
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

    fun saveToCache(catFact: CatFact) {
        val config = RealmConfiguration.with(schema = setOf(CatFactEntity::class))
        Realm.open(config).apply {
            writeBlocking {
                val entity = CatFactEntity()
                entity.fact = catFact.fact
                copyToRealm(entity)
            }
        }
    }

    fun getFromCache(): CatFactEntity {
        val config = RealmConfiguration.with(schema = setOf(CatFactEntity::class))
        return Realm.open(config).query<CatFactEntity>().find().last()
    }
}