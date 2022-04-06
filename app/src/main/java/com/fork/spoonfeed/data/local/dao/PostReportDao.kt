package com.fork.spoonfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fork.spoonfeed.data.local.entity.PostReportData

@Dao
interface PostReportDao {

    @Query("SELECT * FROM report_post_table")
    suspend fun getAllReportedPost(): MutableList<PostReportData>?

    @Insert
    suspend fun insertReportedPost(reportPostData: PostReportData)
}