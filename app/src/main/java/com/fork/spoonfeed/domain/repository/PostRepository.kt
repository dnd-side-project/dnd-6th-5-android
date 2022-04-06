package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.*
import com.fork.spoonfeed.data.remote.model.policy.RequestPolicyLikeData
import com.fork.spoonfeed.data.remote.model.policy.RequestReportData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyLikeData
import com.fork.spoonfeed.data.remote.model.policy.ResponseReportData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData

interface PostRepository {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData

    suspend fun getPostDetail(pk: Int): ResponsePostData

    suspend fun patchPost(pk: Int, body: RequestPatchPostData): ResponsePatchPostData

    suspend fun deletePost(pk: Int): ResponseDeletePostData

    suspend fun searchPost(query: String): ResponseSearchPostAllData

    suspend fun postReport(postPk: Int, body: RequestReportData): ResponseReportData
}
