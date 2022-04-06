package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.local.entity.ReportPostData

interface ReportPostRepository {

    suspend fun getAll(): MutableList<ReportPostData>

    suspend fun insert(reportPostData: ReportPostData)
}