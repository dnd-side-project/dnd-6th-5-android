package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.PostService
import com.fork.spoonfeed.data.remote.model.community.*

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

    override suspend fun deletePost(pk: Int): ResponseDeletePostData {
        return postService.deletePost(pk = pk)
    }

    override suspend fun deleteComment(pk: Int, body: RequestDeleteCommentData): ResponseDeleteCommentData {
        return postService.deleteComment(pk = pk, body = body)
    }
}
