package com.fork.spoonfeed.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class ResponseUserNickNameData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("user") val user: User
    ) {
        data class User(
            @SerializedName("id") val id: String,
            @SerializedName("nickname") val nickname: String,
        )
    }
}
