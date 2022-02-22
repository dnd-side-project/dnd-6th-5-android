package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import javax.inject.Inject

class PolicyDataSourceImpl (
    private val policyService: PolicyService
) : PolicyDataSource {

    override suspend fun getPolicyAll(category: String): ResponsePolicyAllData {
        return policyService.getPolicyAll(category)
    }
}
