package com.demo.kmmshared.core.feature

import com.demo.kmmshared.core.http.HttpRequest
import com.demo.kmmshared.model.FeatureRequest
import com.demo.kmmshared.model.FeatureResponse
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

class FeatureApi {

    companion object {
//        const val BASE_URL = "https://miniapp.dev.mservice.io"
//
//        // path
//        const val GET_ALL_FEATURES_PATH =
//            "/rigver-appversion/v1/features?last_update=0"

        const val BASE_URL = "http://10.40.112.24:8190"

        // path
        const val GET_ALL_FEATURES_PATH =
            "/miniapp/features/list-details"
    }

    private suspend fun sendMessage(
        _path: String,
        _method: HttpMethod = HttpMethod.Post,
        _body: Any? = null
    ): HttpResponse? {
        return HttpRequest.Builder(BASE_URL + _path).apply {
            method = _method
            timeout = 30000L
            retry = 1
            body = _body
        }.build().request()
    }

    suspend fun getAllFeatures(): FeatureResponse? {
        val res = this.sendMessage(_path = GET_ALL_FEATURES_PATH, _body = FeatureRequest(environment = "staging"))
        return res?.receive()
    }
}