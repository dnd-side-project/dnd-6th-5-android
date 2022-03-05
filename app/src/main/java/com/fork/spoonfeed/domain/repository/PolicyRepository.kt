package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.policy.*

interface PolicyRepository {

    suspend fun getPolicyAll(category: String): ResponsePolicyAllData

    suspend fun getPolicyDetail(accessToken: String, platform: String, id: Int): ResponsePolicyDetailData

    suspend fun updateUserInfoAndGetFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy

    suspend fun getFilteredPolicy(body: RequestFilteredPolicy): ResponseFilteredPolicy

    suspend fun postPolicyLike(body: RequestPolicyLikeData): ResponsePolicyLikeData
}
