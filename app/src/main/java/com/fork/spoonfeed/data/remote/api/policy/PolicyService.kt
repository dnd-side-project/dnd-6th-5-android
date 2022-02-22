package com.fork.spoonfeed.data.remote.api.policy

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import retrofit2.http.GET
import retrofit2.http.Headers
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
}

