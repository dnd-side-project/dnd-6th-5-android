package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData

interface CommentDataSource {

    suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData
}
