package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData

interface PostDataSource {

    suspend fun getPostAll(): ResponsePostAllData

    suspend fun getPostDetail(pk: Int): ResponsePostData
}
