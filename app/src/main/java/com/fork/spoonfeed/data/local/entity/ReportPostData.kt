package com.fork.spoonfeed.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_post_table")
data class ReportPostData(
    @PrimaryKey
    @ColumnInfo
    var postPk: Int,
)