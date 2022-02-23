package com.demo.kmmshared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureResponse(
    @SerialName("response_info")
    val responseInfo: ResponseInfo,
    @SerialName("items")
    val items: List<DataItem>,
) {

    @Serializable
    data class ResponseInfo(
        @SerialName("error_message")
        val errorMessage: String,
        @SerialName("error_code")
        val errorCode: Int
    )

    @Serializable
    data class DataItem(
        @SerialName("data")
        val data: FeatureEntity
    )
}