package com.fork.spoonfeed.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class ResponseUserNameDuplicate(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
){
    data class Data(
        @SerializedName("message") val message: String
    )
}
