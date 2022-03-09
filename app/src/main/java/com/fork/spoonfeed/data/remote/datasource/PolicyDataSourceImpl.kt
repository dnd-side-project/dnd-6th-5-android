package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.model.policy.*
import com.google.gson.Gson
import com.google.gson.JsonParser
import javax.inject.Inject

class PolicyDataSourceImpl @Inject constructor(
    private val policyService: PolicyService
) : PolicyDataSource {

    override suspend fun getPolicyAll(category: String): ResponsePolicyAllData {
        return policyService.getPolicyAll(category)
    }

    override suspend fun getPolicyDetail(accessToken: String, platform: String, id: Int): ResponsePolicyDetailData {
        return policyService.getPolicyDetail(accessToken, platform, id)
    }

    override suspend fun updateUserInfoAndGetFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy {
        val jsonString = Gson().toJson(body)
        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        return policyService.updateUserInfoAndGetFilteredPolicy(body = jsonObject)
    }

    override suspend fun getFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy {
        val jsonString = Gson().toJson(body)
        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        return policyService.getFilteredPolicy(body = jsonObject)
    }

    override suspend fun postPolicyLike(body: RequestPolicyLikeData): ResponsePolicyLikeData {
        return policyService.postPolicyLike(body = body)
    }
}


