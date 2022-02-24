package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.community.CommentService
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData

class CommentDataSourceImpl(private val commentService: CommentService) : CommentDataSource {

    override suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData {
        return commentService.postComment(pk = pk, body = body)
    }
}
