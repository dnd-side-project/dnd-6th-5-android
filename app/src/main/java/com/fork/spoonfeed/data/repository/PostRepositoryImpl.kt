package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.PostDataSource
import com.fork.spoonfeed.data.remote.model.community.*
import com.fork.spoonfeed.data.remote.model.policy.RequestPolicyLikeData
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyLikeData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
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

    override suspend fun patchPost(pk: Int, body: RequestPatchPostData): ResponsePatchPostData {
        return postDataSource.patchPost(pk = pk, body = body)
    }

    override suspend fun deletePost(pk: Int): ResponseDeletePostData {
        return postDataSource.deletePost(pk = pk)
    }
}
