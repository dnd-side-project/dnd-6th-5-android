package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.user.RequestQuestionData
import com.fork.spoonfeed.data.remote.model.user.ResponseQuestionData

interface QuestionRepository {
    suspend fun postQuestion(body: RequestQuestionData): ResponseQuestionData
}
