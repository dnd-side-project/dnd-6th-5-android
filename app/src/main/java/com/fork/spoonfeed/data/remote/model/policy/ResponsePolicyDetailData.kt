package com.fork.spoonfeed.data.remote.model.policy

import com.google.gson.annotations.SerializedName

data class ResponsePolicyDetailData(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: Data
) {
    data class Data(
        @SerializedName("policy")
        val policy: Policy
    ) {
        data class Policy(
            @SerializedName("name")
            val name: String,
            @SerializedName("number")
            val number: String,
            @SerializedName("category")
            val category: String,
            @SerializedName("summary")
            val summary: String,
            @SerializedName("host")
            val host: String,
            @SerializedName("applicationPeriod")
            val applicationPeriod: String,
            @SerializedName("announcement")
            val announcement: String,
            @SerializedName("policyDuration")
            val policyDuration: String,
            @SerializedName("limitAge")
            val limitAge: String,
            @SerializedName("limitAreaAsset")
            val limitAreaAsset: String,
            @SerializedName("specialization")
            val specialization: String,
            @SerializedName("content")
            val content: String,
            @SerializedName("note")
            val note: String,
            @SerializedName("limitedTarget")
            val limitedTarget: String,
            @SerializedName("supportScale")
            val supportScale: String,
            @SerializedName("applicationProcess")
            val applicationProcess: String,
            @SerializedName("applicationSite")
            val applicationSite: String,
            @SerializedName("applicationSiteName")
            val applicationSiteName: String,
            @SerializedName("submission")
            val submission: String,
            @SerializedName("otherInfo")
            val otherInfo: String,
            @SerializedName("referenceSite1")
            val referenceSite1: String,
            @SerializedName("referenceSite2")
            val referenceSite2: String,
            @SerializedName("likeCount")
            val likeCount: String,
            @SerializedName("operatingInstitute")
            val operatingInstitute: String,
            )
    }
}
