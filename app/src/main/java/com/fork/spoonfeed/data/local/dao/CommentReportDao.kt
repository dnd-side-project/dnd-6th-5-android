package com.fork.spoonfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fork.spoonfeed.data.local.entity.CommentReportData

@Dao
interface CommentReportDao {

    @Query("SELECT * FROM report_comment_table")
    suspend fun getAllReportedComment(): List<CommentReportData>

    @Insert
    suspend fun insertReportedComment(reportCommentData: CommentReportData)
}
