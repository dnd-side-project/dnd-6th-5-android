package com.fork.spoonfeed.data.remote.api.user

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.user.*
import retrofit2.http.*

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

    @PATCH("user")
    suspend fun patchUserFilter(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Body body: RequestPatchUserFilterData
    ): ResponsePatchUserFilterData

    @GET("user/check-duplicate")
    suspend fun checkUserNameDuplicate(
        @Query("nickname") nickName: String
    ): ResponseUserNameDuplicate

    @POST("user/{userId}/block/{blockedId}")
    suspend fun blockUser(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("userId") userId: Int = UserData.id!!,
        @Path("blockedId") blockedId: Int,
    ): ResponseBlockUserData
}
