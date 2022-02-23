package com.fork.spoonfeed.data.remote.api.policy

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.policy.ResponseFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PolicyService {

    @GET("policy")
    suspend fun getPolicyAll(@Query("category") category: String): ResponsePolicyAllData

    @Headers("platform:kakao")
    @GET("policy/{pk}")
    suspend fun getPolicyDetail(
        @Path("pk")
        pk: Int
    ): ResponsePolicyDetailData

    @POST("custom/policy")
    suspend fun updateUserInfoAndGetFilteredPolicy(
        @Header("access_token") accessToken: String = UserData.accessToken,
        @Header("platform") platform: String = UserData.platform,
        @Body body: JsonObject
    ) : ResponseFilteredPolicy

    @GET("custom/policy")
    suspend fun getFilteredPolicy(
        @Header("access_token") accessToken: String = UserData.accessToken,
        @Header("platform") platform: String = UserData.platform,
        @Body body: JsonObject
    ) : ResponseFilteredPolicy
}

