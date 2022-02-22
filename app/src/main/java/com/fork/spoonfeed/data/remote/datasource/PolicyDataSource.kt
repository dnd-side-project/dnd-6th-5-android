package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData

interface PolicyDataSource {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData
}
