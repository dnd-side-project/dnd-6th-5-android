package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponseFilteredPolicy
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData

interface PolicyRepository {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData

    suspend fun getPolicyDetail(pk: Int): ResponsePolicyDetailData

    suspend fun updateUserInfoAndGetFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy

    suspend fun getFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy
}
