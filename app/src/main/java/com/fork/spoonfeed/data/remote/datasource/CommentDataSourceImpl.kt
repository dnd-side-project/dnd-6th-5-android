package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.CommentService
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestPatchCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePatchCommentData
import javax.inject.Inject

class CommentDataSourceImpl @Inject constructor(private val commentService: CommentService) : CommentDataSource {

    override suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData {
        return commentService.postComment(pk = pk, body = body)
    }

    override suspend fun patchComment(
        pk: Int,
        body: RequestPatchCommentData
    ): ResponsePatchCommentData {
        return commentService.patchComment(pk = pk, body = body)
    }

    override suspend fun deleteComment(pk: Int, body: RequestDeleteCommentData): ResponseDeleteCommentData {
        return commentService.deleteComment(pk = pk, body = body)
    }
}
