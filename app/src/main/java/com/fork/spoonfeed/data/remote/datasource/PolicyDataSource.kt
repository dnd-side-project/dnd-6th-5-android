package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData

interface PolicyDataSource {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData

    suspend fun getPolicyDetail(pk: Int): ResponsePolicyDetailData
}
