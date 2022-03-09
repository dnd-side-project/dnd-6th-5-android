package com.fork.spoonfeed.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class ResponseUserLikePolicyData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("policy") val policy: List<Policy>,
    ) {
        data class Policy(
            @SerializedName("user_id") val userId: Int,
            @SerializedName("policy_id") val policyId: Int,
            @SerializedName("category") val category: String,
            @SerializedName("name") val NAME: String,
            @SerializedName("content") val content: String,
            @SerializedName("application_period") val applicationPeriod: String,
            @SerializedName("cnt") val cnt: String,
        )
    }
}