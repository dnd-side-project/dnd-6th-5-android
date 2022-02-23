package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData

interface PolicyRepository {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData
    suspend fun getPolicyDetail(pk: Int): ResponsePolicyDetailData
}
