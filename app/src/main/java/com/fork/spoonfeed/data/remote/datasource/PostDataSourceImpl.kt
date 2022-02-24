package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.PostService
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData

class PostDataSourceImpl(private val postService: PostService): PostDataSource {

    override suspend fun getPostAll(): ResponsePostAllData {
        return postService.getPostAll()
    }

    override suspend fun getPostDetail(pk: Int): ResponsePostData {
        return postService.getPostDetail(pk = pk)
    }
}
