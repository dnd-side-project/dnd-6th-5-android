package com.fork.spoonfeed.data.remote.api.community

import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPostAll(): ResponsePostAllData
}
