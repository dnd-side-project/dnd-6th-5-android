package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.model.policy.*
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
        return policyService.updateUserInfoAndGetFilteredPolicy(body = body)
    }

    override suspend fun getFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy {
        return policyService.getFilteredPolicy(body = body)
    }

    override suspend fun postPolicyLike(body: RequestPolicyLikeData): ResponsePolicyLikeData {
        return policyService.postPolicyLike(body = body)
    }
}


