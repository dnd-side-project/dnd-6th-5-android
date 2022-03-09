package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.QuestionDataSource
import com.fork.spoonfeed.data.remote.model.user.RequestQuestionData
import com.fork.spoonfeed.data.remote.model.user.ResponseQuestionData
import com.fork.spoonfeed.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val questionDataSource: QuestionDataSource
) : QuestionRepository {

    override suspend fun postQuestion(body: RequestQuestionData): ResponseQuestionData {
        return questionDataSource.postQuestion(body = body)
    }
}