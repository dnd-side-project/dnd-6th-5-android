package com.fork.spoonfeed.data.remote.model.policy

import com.google.gson.annotations.SerializedName

data class ResponseFilteredPolicy(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
){
    data class Data(
        @SerializedName("newUser") val newUser: User,
        @SerializedName("policy") val policy: List<Policy>,
    ){
        data class User(
            @SerializedName("id") val id: Int,
            @SerializedName("nickname") val nickname: String,
            @SerializedName("age") val age: String,
            @SerializedName("workStatus") val workStatus: String,
            @SerializedName("companyScale") val companyScale: String,
            @SerializedName("medianIncome") val medianIncome: String,
            @SerializedName("annualIncome") val annualIncome: String,
            @SerializedName("asset") val asset: String,
            @SerializedName("hasHouse") val hasHouse: String,
            @SerializedName("isHouseOwner") val isHouseOwner: String,
            @SerializedName("maritalStatus") val maritalStatus: String,
            @SerializedName("email") val email: String,
            @SerializedName("createdAt") val createdAt: String,
            @SerializedName("updatedAt") val updatedAt: String,
        )
        data class Policy(
            @SerializedName("policy_id") val id: Int,
            @SerializedName("name") val name: String,
            @SerializedName("category") val category: String,
            @SerializedName("summary") val summary: String,
            @SerializedName("application_period") val applicationPeriod: String,
            @SerializedName("like_cnt") val likeCnt: String,
        )
    }
}
