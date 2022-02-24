package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.community.ResponseSendPostData

interface PostRepository {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData

    suspend fun getPostDetail(pk: Int): ResponsePostData
}
