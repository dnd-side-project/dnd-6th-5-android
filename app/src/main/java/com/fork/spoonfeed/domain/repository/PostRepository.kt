package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.*
import com.fork.spoonfeed.data.remote.model.community.RequestPostReportData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostReportData

interface PostRepository {

    suspend fun getPostAll(userId: Int): ResponsePostAllData

    suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData

    suspend fun getPostDetail(pk: Int): ResponsePostData

    suspend fun patchPost(pk: Int, body: RequestPatchPostData): ResponsePatchPostData

    suspend fun deletePost(pk: Int): ResponseDeletePostData

    suspend fun searchPost(query: String): ResponseSearchPostAllData

    suspend fun postReport(postPk: Int, body: RequestPostReportData): ResponsePostReportData
}
