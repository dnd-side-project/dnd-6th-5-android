package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class ResponsePostData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("post") val post: Post,
        @SerializedName("comment") val comment: List<Comment>
    ) {
        data class Post(
            @SerializedName("title") val title: String,
            @SerializedName("category") val category: String,
            @SerializedName("content") val content: String,
            @SerializedName("author") val author: String,
            @SerializedName("age") val age: String,
            @SerializedName("maritalStatus") val maritalStatus: String,
            @SerializedName("workStatus") val workStatus: String,
            @SerializedName("companyScale") val companyScale: String,
            @SerializedName("medianIncome") val medianIncome: String,
            @SerializedName("annualIncome") val annualIncome: String,
            @SerializedName("asset") val asset: String,
            @SerializedName("hasHouse") val hasHouse: String,
            @SerializedName("isHouseOwner") val isHouseOwner: String,
            @SerializedName("commentCount") val commentCount: String,
            @SerializedName("createdAt") val createdAt: String,
            @SerializedName("updatedAt") val updatedAt: String,
        )
        data class Comment(
            @SerializedName("id") val id: Int,
            @SerializedName("content") val content: String,
            @SerializedName("commenter") val commenter: String,
            @SerializedName("createdAt") val createdAt: String,
            @SerializedName("updatedAt") val updatedAt: String,
        )
    }
}
