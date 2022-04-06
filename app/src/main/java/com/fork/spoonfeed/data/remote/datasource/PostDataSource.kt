package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.community.RequestPatchPostData
import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponseDeletePostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePatchPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.community.ResponseSearchPostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponseSendPostData
import com.fork.spoonfeed.data.remote.model.policy.RequestReportData
import com.fork.spoonfeed.data.remote.model.policy.ResponseReportData

interface PostDataSource {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData

    suspend fun getPostDetail(pk: Int): ResponsePostData

    suspend fun patchPost(pk: Int, body: RequestPatchPostData): ResponsePatchPostData

    suspend fun deletePost(pk: Int): ResponseDeletePostData

    suspend fun searchPost(query: String): ResponseSearchPostAllData

    suspend fun postReport(postPk: Int, body: RequestReportData): ResponseReportData
}
