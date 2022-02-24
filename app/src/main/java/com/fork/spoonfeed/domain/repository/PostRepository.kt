package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData

interface PostRepository {

    suspend fun getPostAll(): ResponsePostAllData
}
