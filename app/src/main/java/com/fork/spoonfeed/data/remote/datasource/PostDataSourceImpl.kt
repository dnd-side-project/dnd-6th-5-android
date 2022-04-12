package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.PostService
import com.fork.spoonfeed.data.remote.model.community.*
import com.fork.spoonfeed.data.remote.model.community.RequestPostReportData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostReportData
import javax.inject.Inject

class PostDataSourceImpl @Inject constructor(private val postService: PostService) : PostDataSource {

    override suspend fun getPostAll(userId: Int): ResponsePostAllData {
        return postService.getPostAll(userId = userId)
    }

    override suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData {
        return postService.sendPost(body = body)
    }

    override suspend fun getPostDetail(pk: Int): ResponsePostData {
        return postService.getPostDetail(pk = pk)
    }

    override suspend fun patchPost(pk: Int, body: RequestPatchPostData): ResponsePatchPostData {
        return postService.patchPost(pk = pk, body = body)
    }

    override suspend fun deletePost(pk: Int): ResponseDeletePostData {
        return postService.deletePost(pk = pk)
    }

    override suspend fun searchPost(query: String): ResponseSearchPostAllData {
        return postService.searchPost(query)
    }

    override suspend fun postReport(postPk: Int, body: RequestPostReportData): ResponsePostReportData {
        return postService.postPostReport(postPk = postPk, body = body)
    }
}
