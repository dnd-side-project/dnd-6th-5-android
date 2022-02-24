package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData

interface PostDataSource {

    suspend fun getPostAll(): ResponsePostAllData
}
