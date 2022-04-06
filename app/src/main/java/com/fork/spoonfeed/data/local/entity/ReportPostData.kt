package com.fork.spoonfeed.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "report_post_table")
data class ReportPostData(
    @ColumnInfo
    var postPk: Int,
)