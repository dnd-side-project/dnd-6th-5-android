package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.*
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData

interface PostRepository {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData

    suspend fun getPostDetail(pk: Int): ResponsePostData

    suspend fun patchPost(pk: Int, body: RequestPatchPostData): ResponsePatchPostData

    suspend fun deletePost(pk: Int): ResponseDeletePostData
}
