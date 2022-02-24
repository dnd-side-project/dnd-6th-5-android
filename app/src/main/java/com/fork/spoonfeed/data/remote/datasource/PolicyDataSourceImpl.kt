package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
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
}


