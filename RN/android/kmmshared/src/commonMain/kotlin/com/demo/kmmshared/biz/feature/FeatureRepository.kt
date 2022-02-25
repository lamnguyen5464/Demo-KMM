package com.demo.kmmshared.biz.feature

import com.demo.kmmshared.biz.feature.model.FeatureResponse

class FeatureRepository {
    private val featureApi = FeatureApi()

    @Throws(Exception::class)
    suspend fun getAllFeatures(): FeatureResponse? {
        return featureApi.getAllFeatures()
    }
}