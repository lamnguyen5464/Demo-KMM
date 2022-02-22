package com.demo.kmm

import com.demo.kmm.model.FeatureResponse

class FeatureRepository {
    private val featureApi = FeatureApi()

    @Throws(Exception::class)
    suspend fun getAllFeatures(): FeatureResponse {
        return featureApi.getAllFeatures()
    }
}