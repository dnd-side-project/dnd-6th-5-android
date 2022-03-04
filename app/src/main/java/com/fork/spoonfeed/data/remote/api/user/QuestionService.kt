package com.fork.spoonfeed.data.remote.api.user

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.user.RequestQuestionData
import com.fork.spoonfeed.data.remote.model.user.ResponseQuestionData
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface QuestionService {

    @POST("question")
    suspend fun postQuestion(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
        @Header("platform") platform: String = UserData.platform!!,
        @Body body: RequestQuestionData
    ): ResponseQuestionData
}