package com.fork.spoonfeed.data.remote.model.policy

import com.google.gson.annotations.SerializedName

data class ResponsePolicyAllData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
){
    data class Data(
        @SerializedName("policy") val policy: List<Policy>
    ){
        data class Policy(
            @SerializedName("id") val id: Int,
            @SerializedName("name") val name: String,
            @SerializedName("category") val category: String,
            @SerializedName("summary") val summary: String,
            @SerializedName("applicationPeriod") val applicationPeriod: String,
            @SerializedName("likeCount") val likeCount: String
        )
    }
}
