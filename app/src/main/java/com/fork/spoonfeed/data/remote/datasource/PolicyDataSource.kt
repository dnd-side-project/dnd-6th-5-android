package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.*
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PolicyDataSource {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData

    suspend fun getPolicyDetail(accessToken: String, platform: String, id: Int): ResponsePolicyDetailData

    suspend fun updateUserInfoAndGetFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy

    suspend fun getFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy

    suspend fun postPolicyLike(body: RequestPolicyLikeData): ResponsePolicyLikeData
}
