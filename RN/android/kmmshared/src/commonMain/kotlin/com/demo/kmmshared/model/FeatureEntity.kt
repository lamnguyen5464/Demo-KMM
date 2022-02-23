package com.demo.kmmshared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureEntity(
    @SerialName("appId")
    val appId: String,
    @SerialName("code")
    val code: String,
    @SerialName("config")
    val config: String,
    @SerialName("name")
    val name: String,
    @SerialName("icon")
    val icon: String
)