package com.fork.spoonfeed.data.remote.api.policy

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import retrofit2.http.GET
import retrofit2.http.Query

interface PolicyService {

    @GET("policy")
    suspend fun getPolicyAll(@Query("category") category: String): ResponsePolicyAllData
}
