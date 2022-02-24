package com.fork.spoonfeed.data.remote.api.community

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {

    @POST("posts/{pk}/comment")
    suspend fun postComment(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Path("pk") pk: Int,
        @Body body: RequestCommentData
    ): ResponseCommentData
}
