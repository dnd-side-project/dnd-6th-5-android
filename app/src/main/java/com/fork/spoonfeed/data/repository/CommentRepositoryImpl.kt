package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.CommentDataSource
import com.fork.spoonfeed.data.remote.model.community.RequestCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestPatchCommentData
import com.fork.spoonfeed.data.remote.model.community.RequestPostReportData
import com.fork.spoonfeed.data.remote.model.community.ResponseCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponseDeleteCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePatchCommentData
import com.fork.spoonfeed.data.remote.model.community.ResponsePostReportData
import com.fork.spoonfeed.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val commentDataSource: CommentDataSource) :
    CommentRepository {

    override suspend fun postComment(pk: Int, body: RequestCommentData): ResponseCommentData {
        return commentDataSource.postComment(pk = pk, body = body)
    }

    override suspend fun patchComment(
        pk: Int,
        body: RequestPatchCommentData
    ): ResponsePatchCommentData {
        return commentDataSource.patchComment(pk = pk, body = body)
    }

    override suspend fun deleteComment(
        pk: Int,
        body: RequestDeleteCommentData
    ): ResponseDeleteCommentData {
        return commentDataSource.deleteComment(pk = pk, body = body)
    }

    override suspend fun reportComment(
        commentPk: Int,
        body: RequestPostReportData
    ): ResponsePostReportData {
        return commentDataSource.reportComment(commentPk = commentPk, body = body)
    }
}
