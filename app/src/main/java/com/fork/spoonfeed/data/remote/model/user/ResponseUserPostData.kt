package com.fork.spoonfeed.data.remote.model.user

import com.google.gson.annotations.SerializedName


data class ResponseUserPostData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("post") val post: List<Post?>,
    ) {
        data class Post(
            @SerializedName("user_id") val userId: Int,
            @SerializedName("post_id") val postId: Int,
            @SerializedName("category") val category: String,
            @SerializedName("title") val title: String,
            @SerializedName("content") val content: String,
            @SerializedName("cnt") val cnt: String,
            @SerializedName("createdAt") val createdAt: String,
        )
    }
}
