package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.PostService
import com.fork.spoonfeed.data.remote.model.community.*
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData

class PostDataSourceImpl(private val postService: PostService) : PostDataSource {

    override suspend fun getPostAll(): ResponsePostAllData {
        return postService.getPostAll()
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

    override suspend fun deletePost(pk: Int): ResponseDeletePost {
        return postService.deletePost(pk = pk)
    }
}
