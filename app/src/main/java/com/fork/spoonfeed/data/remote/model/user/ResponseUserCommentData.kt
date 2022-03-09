package com.fork.spoonfeed.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class ResponseUserCommentData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("comment") val comment: List<Comment?>,
    ) {
        data class Comment(
            @SerializedName("user_id") val userId: Int,
            @SerializedName("post_id") val postId: Int,
            @SerializedName("comment_id") val commentId: String,
            @SerializedName("title") val title: String,
            @SerializedName("content") val content: String,
            @SerializedName("createdAt") val createdAt: String,
        )
    }
}