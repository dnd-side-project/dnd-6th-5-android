package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.PostService
import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.community.ResponseSendPostData

class PostDataSourceImpl(private val postService: PostService): PostDataSource {

    override suspend fun getPostAll(): ResponsePostAllData {
        return postService.getPostAll()
    }

    override suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData {
        return postService.sendPost(body = body)
    }

    override suspend fun getPostDetail(pk: Int): ResponsePostData {
        return postService.getPostDetail(pk = pk)
    }
}
