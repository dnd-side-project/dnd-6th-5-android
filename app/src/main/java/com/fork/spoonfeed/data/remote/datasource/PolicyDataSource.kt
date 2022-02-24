package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponseFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData

interface PolicyDataSource {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData

    suspend fun getPolicyDetail(accessToken: String, platform: String, id: Int): ResponsePolicyDetailData

    suspend fun updateUserInfoAndGetFilteredPolicy(body: RequestFilteredPolicy) : ResponseFilteredPolicy

    suspend fun getFilteredPolicy(body: RequestFilteredPolicy) : ResponseFilteredPolicy
}
