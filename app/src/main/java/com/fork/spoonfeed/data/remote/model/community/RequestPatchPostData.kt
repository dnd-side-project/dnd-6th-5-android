package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class RequestPatchPostData(
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("content") val content: String,
    @SerializedName("age") val age: String,
    @SerializedName("maritalStatus") val maritalStatus: String,
    @SerializedName("workStatus") val workStatus: String,
    @SerializedName("companyScale") val companyScale: String,
    @SerializedName("medianIncome") val medianIncome: String,
    @SerializedName("annualIncome") val annualIncome: String,
    @SerializedName("asset") val asset: String,
    @SerializedName("isHouseOwner") val isHouseOwner: String,
    @SerializedName("hasHouse") val hasHouse: String,
)