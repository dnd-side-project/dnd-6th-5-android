package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.user.RequestQuestionData
import com.fork.spoonfeed.data.remote.model.user.ResponseQuestionData

interface QuestionDataSource {
    suspend fun postQuestion(body: RequestQuestionData): ResponseQuestionData
}