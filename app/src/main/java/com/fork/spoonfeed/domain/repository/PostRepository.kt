package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData

interface PostRepository {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun getPostDetail(pk: Int): ResponsePostData
}
