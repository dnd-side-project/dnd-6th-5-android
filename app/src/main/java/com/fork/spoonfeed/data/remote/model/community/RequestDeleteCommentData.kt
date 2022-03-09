package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class RequestDeleteCommentData(
    @SerializedName("commentId")   val commentId: Int
)