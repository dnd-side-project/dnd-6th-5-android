package com.fork.spoonfeed.data.remote.model.policy

import com.google.gson.annotations.SerializedName

data class ResponsePolicyLikeData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Message
){
    data class Message(
        @SerializedName("message") val message: String
    )
}