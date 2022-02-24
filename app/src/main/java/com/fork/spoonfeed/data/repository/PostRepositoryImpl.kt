package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.PostDataSource
import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.community.ResponseSendPostData
import com.fork.spoonfeed.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postDataSource: PostDataSource) : PostRepository {

    override suspend fun getPostAll(): ResponsePostAllData {
        return postDataSource.getPostAll()
    }

    override suspend fun sendPost(body: RequestSendPostData): ResponseSendPostData {
        return postDataSource.sendPost(body)
    }

    override suspend fun getPostDetail(pk: Int): ResponsePostData {
        return postDataSource.getPostDetail(pk)
    }
}
