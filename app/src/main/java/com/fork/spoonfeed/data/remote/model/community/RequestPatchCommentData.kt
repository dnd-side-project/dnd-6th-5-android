package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class RequestPatchCommentData(
    @SerializedName("commentId") val commentId: Int,
    @SerializedName("content") val content: String
)
