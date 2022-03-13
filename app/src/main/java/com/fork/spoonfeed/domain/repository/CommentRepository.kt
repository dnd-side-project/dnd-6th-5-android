package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestPatchCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePatchCommentData

interface CommentRepository {

    suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData

    suspend fun patchComment(pk: Int, body: RequestPatchCommentData): ResponsePatchCommentData

    suspend fun deleteComment(pk: Int, body: RequestDeleteCommentData): ResponseDeleteCommentData
}
