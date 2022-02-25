package com.demo.kmmshared.biz.feature.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureRequest(
    @SerialName("environment")
    val environment: String = "development",
)