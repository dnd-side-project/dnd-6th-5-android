package com.fork.spoonfeed.data.remote.api.community

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.RequestSendPostData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.community.ResponseSendPostData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {

    @GET("posts")
    suspend fun getPostAll(): ResponsePostAllData

    @POST("posts")
    suspend fun sendPost(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Body body: RequestSendPostData
    ): ResponseSendPostData

    @GET("posts/{pk}")
    suspend fun getPostDetail(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("pk") pk: Int
    ): ResponsePostData
}
