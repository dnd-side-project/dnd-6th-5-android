package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.PostDataSource
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postDataSource: PostDataSource) : PostRepository {

    override suspend fun getPostAll(): ResponsePostAllData {
        return postDataSource.getPostAll()
    }
}
