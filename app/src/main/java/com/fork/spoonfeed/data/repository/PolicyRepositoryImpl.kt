package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.PolicyDataSource
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponseFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.fork.spoonfeed.domain.repository.PolicyRepository
import javax.inject.Inject

class PolicyRepositoryImpl @Inject constructor(
    private val policyDataSource: PolicyDataSource
) : PolicyRepository {

    override suspend fun getPolicyAll(category: String): ResponsePolicyAllData {
        return policyDataSource.getPolicyAll(category)
    }

    override suspend fun getPolicyDetail(accessToken: String, platform: String, id: Int): ResponsePolicyDetailData {
        return policyDataSource.getPolicyDetail(accessToken, platform, id)
    }

    override suspend fun updateUserInfoAndGetFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy {
        return policyDataSource.updateUserInfoAndGetFilteredPolicy(body = body)
    }

    override suspend fun getFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy {
        return policyDataSource.getFilteredPolicy(body = body)
    }
}
