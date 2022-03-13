package com.fork.spoonfeed.data.remote.api.community

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.ResponseSearchPostAllData
import com.fork.spoonfeed.data.remote.model.community.*
import retrofit2.http.*
import retrofit2.http.Body


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

    @PATCH("posts/{pk}")
    suspend fun patchPost(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("pk") pk: Int,
        @Body body: RequestPatchPostData
    ): ResponsePatchPostData

    @DELETE("posts/{pk}")
    suspend fun deletePost(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("pk") pk: Int,
    ): ResponseDeletePostData

    @GET("posts/search")
    suspend fun searchPost(
        @Query("query") query: String
    ): ResponseSearchPostAllData
}
