package com.fork.spoonfeed.data.remote.api.user

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserService {

    @GET("/user/{userId}")
    suspend fun getUserData(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("userId") userId: Int = UserData.id!!
    ): ResponseUserData

    @PATCH("user/nickname")
    suspend fun patchUserNickName(
        @Header("access_token") accessToken: String,
        @Header("platform") platform: String,
        @Body body: RequestUserNickNameData
    ): ResponseUserNickNameData

    @GET("user/{userId}/post")
    suspend fun getUserPost(
        @Header("access_token") accessToken: String,
        @Header("platform") platform: String,
        @Path("userId") id: Int,
    ): ResponseUserPostData

    @GET("user/{userId}/comment")
    suspend fun getUserComment(
        @Header("access_token") accessToken: String,
        @Header("platform") platform: String,
        @Path("userId") id: Int,
    ): ResponseUserCommentData

    @GET("user/{userId}/like/policy")
    suspend fun getUserLikePolicy(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("userId") userId: Int = UserData.id!!
    ): ResponseUserLikePolicyData
}
