package com.fork.spoonfeed.data.remote.api.user

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import retrofit2.http.*

interface UserService {
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
}