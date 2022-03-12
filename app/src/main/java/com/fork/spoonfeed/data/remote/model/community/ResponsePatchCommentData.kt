package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class ResponsePatchCommentData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data,
){
    data class Data(
        @SerializedName("comment") val comment: Comment
    ){
        data class Comment(
            @SerializedName("userId") val userId: Int,
            @SerializedName("commentId") val commentId: Int,
            @SerializedName("content") val content: String,
            @SerializedName("createdAt") val createdAt: String,
            @SerializedName("updatedAt") val updatedAt: String,
        )
    }
}
