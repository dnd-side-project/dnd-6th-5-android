package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.user.QuestionService
import com.fork.spoonfeed.data.remote.model.user.RequestQuestionData
import com.fork.spoonfeed.data.remote.model.user.ResponseQuestionData
import javax.inject.Inject

class QuestionDataSourceImpl @Inject constructor(private val questionService: QuestionService) : QuestionDataSource {

    override suspend fun postQuestion(body: RequestQuestionData): ResponseQuestionData {
        return questionService.postQuestion(body = body)
    }
}