package com.fork.spoonfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fork.spoonfeed.data.local.entity.ReportPostData

@Dao
interface ReportPostDao {

    @Query("SELECT * FROM report_post_table")
    suspend fun getAll(): MutableList<ReportPostData>

    @Insert
    suspend fun insert(reportPostData: ReportPostData)
}