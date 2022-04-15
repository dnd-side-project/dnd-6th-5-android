package com.fork.spoonfeed.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_comment_table")
data class CommentReportData(
    @PrimaryKey
    @ColumnInfo
    var commentPk: Int,
)
