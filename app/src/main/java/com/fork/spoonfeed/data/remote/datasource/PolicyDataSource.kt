package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PolicyDataSource {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData

    suspend fun getPolicyDetail(accessToken: String, platform: String, id: Int): ResponsePolicyDetailData
}
