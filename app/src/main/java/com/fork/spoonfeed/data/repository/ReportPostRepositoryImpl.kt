package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.local.dao.ReportPostDao
import com.fork.spoonfeed.data.local.entity.ReportPostData
import com.fork.spoonfeed.domain.repository.ReportPostRepository
import javax.inject.Inject

class ReportPostRepositoryImpl @Inject constructor(
    private val reportPostDao: ReportPostDao
) : ReportPostRepository {
    override suspend fun getAll(): MutableList<ReportPostData> {
        return reportPostDao.getAll()
    }

    override suspend fun insert(reportPostData: ReportPostData) {
        return reportPostDao.insert(reportPostData)
    }
}