package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.community.ResponseSendPostData

interface PostDataSource {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData

    suspend fun getPostDetail(pk: Int): ResponsePostData
}
