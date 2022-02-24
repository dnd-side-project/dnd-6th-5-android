package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.CommentDataSource
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import com.fork.spoonfeed.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val commentDataSource: CommentDataSource) : CommentRepository {

    override suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData {
        return commentDataSource.postComment(pk = pk, body = body)
    }
}
