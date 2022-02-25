package com.demo.kmmshared.biz.feature

import com.demo.kmmshared.core.http.MHttpRequest
import com.demo.kmmshared.biz.feature.model.FeatureRequest
import com.demo.kmmshared.biz.feature.model.FeatureResponse
import com.demo.kmmshared.core.http.HttpExecutor
import com.demo.kmmshared.core.http.MHttpResponse
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

class FeatureApi {

    companion object {
        const val BASE_URL = "https://miniapp.dev.mservice.io"

        // path
        const val GET_ALL_FEATURES_PATH =
            "/rigver-appversion/v1/features?last_update=0"

//        const val BASE_URL = "http://10.40.112.24:8190"
//        // path
//        const val GET_ALL_FEATURES_PATH =
//            "/miniapp/features/list-details"
    }

    private suspend fun sendMessage(
        _path: String,
        _method: HttpMethod = HttpMethod.Get,
        _body: Any? = null
    ): MHttpResponse? {
        val httpExecutor = HttpExecutor()
        return httpExecutor.execute(
            MHttpRequest.Builder(url = BASE_URL + _path, method = _method).apply {
                timeout = 30000L
                retry = 1
                body = _body
            }.appendHeader("Authorization", "lam.nguyen5").build()
        )
    }

    suspend fun getAllFeatures(): FeatureResponse? {
        val res = this.sendMessage(
            _path = GET_ALL_FEATURES_PATH,
            _body = FeatureRequest(environment = "staging")
        )
        return res?.receive()
    }
}