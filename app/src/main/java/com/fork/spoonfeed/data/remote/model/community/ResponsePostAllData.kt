package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class ResponsePostAllData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("post") val post: List<Post>,
    ) {
        data class Post(
            @SerializedName("id") val id: Int,
            @SerializedName("author") val author: String,
            @SerializedName("title") val title: String,
            @SerializedName("category") val category: String,
            @SerializedName("content") val content: String,
            @SerializedName("commentCount") val commentCount: String,
            @SerializedName("createdAt") val createdAt: String,
            @SerializedName("updatedAt") val updatedAt: String,
        )
    }
}
