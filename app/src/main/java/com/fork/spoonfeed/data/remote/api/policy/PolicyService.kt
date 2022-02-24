package com.fork.spoonfeed.data.remote.api.policy

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import retrofit2.http.*

interface PolicyService {

    @GET("policy")
    suspend fun getPolicyAll(@Query("category") category: String): ResponsePolicyAllData


    @GET("policy/{pk}")
    suspend fun getPolicyDetail(
        @Header("access_token") accessToken: String,
        @Header("platform") platform: String,
        @Path("pk") id: Int,
    ): ResponsePolicyDetailData

}
