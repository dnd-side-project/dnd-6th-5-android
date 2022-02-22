package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData

interface PolicyRepository {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData
}
