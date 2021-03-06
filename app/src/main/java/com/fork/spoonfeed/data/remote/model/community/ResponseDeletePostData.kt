package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class ResponseDeletePostData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("message") val message: String
    )
}