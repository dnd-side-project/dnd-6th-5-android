package com.fork.spoonfeed.data.remote.model.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseQuestionData(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: Data
) {
    data class Data(
        @SerializedName("newQuestion") val newQuestion: NewQuestion,
    ) {
        data class NewQuestion(
            @SerializedName("id") val id: Int,
            @SerializedName("title") val title: String,
            @SerializedName("created_at") val createdAt: String,
            @SerializedName("updated_at") val updatedAt: String,
            @SerializedName("content") val content: String,
            @SerializedName("user") val user: User
        ) {
            data class User(
                @SerializedName("id") val id: Int,
                @SerializedName("nickname") val nickname: String,
                @SerializedName("age") val age: String,
                @SerializedName("workStatus") val workStatus: String,
                @SerializedName("companyScale") val companyScale: String,
                @SerializedName("medianIncome") val medianIncome: String,
                @SerializedName("annualIncome") val annualIncome: String,
                @SerializedName("asset") val asset: String,
                @SerializedName("hasHouse") val hasHouse: String,
                @SerializedName("isHouseOwner") val isHouseOwner: String,
                @SerializedName("maritalStatus") val maritalStatus: String,
                @SerializedName("email") val email: String,
                @SerializedName("createdAt") val createdAt: String,
                @SerializedName("updatedAt") val updatedAt: String,
            ) : Serializable
        }
    }
}
