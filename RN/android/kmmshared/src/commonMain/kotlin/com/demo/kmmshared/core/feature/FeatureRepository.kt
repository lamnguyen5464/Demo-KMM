package com.demo.kmmshared.core.feature

import com.demo.kmmshared.model.FeatureResponse

class FeatureRepository {
    private val featureApi = FeatureApi()

    @Throws(Exception::class)
    suspend fun getAllFeatures(): FeatureResponse? {
        return featureApi.getAllFeatures()
    }
}