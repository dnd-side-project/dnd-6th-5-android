package com.fork.spoonfeed.data.remote.api.community

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseDeleteCommentData
import retrofit2.http.*

interface CommentService {

    @POST("posts/{pk}/comment")
    suspend fun postComment(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("pk") pk: Int,
        @Body body: RequestCommentData
    ): ResponseCommentData

    @HTTP(method = "DELETE", path = "posts/{pk}/comment", hasBody = true)
    suspend fun deleteComment(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("pk") pk: Int,
        @Body body: RequestDeleteCommentData
    ): ResponseDeleteCommentData
}
