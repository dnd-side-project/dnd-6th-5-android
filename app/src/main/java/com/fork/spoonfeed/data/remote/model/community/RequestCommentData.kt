package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class RequestCommentData(
    @SerializedName("userId") val userId: Int,
    @SerializedName("content") val content: String
)
