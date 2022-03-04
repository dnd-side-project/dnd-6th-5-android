package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseDeleteCommentData

interface CommentDataSource {

    suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData

    suspend fun deleteComment(pk: Int, body: RequestDeleteCommentData): ResponseDeleteCommentData
}
