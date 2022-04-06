package com.fork.spoonfeed.data.remote.model.community

import com.google.gson.annotations.SerializedName

data class ResponsePostReportData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Message
){
    data class Message(
        @SerializedName("message") val message: String
    )
}