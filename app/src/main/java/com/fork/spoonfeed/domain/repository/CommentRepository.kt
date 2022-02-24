package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData

interface CommentRepository {

    suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData
}
