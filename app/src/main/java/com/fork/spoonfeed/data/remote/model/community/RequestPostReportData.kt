package com.fork.spoonfeed.data.remote.model.community

import java.io.Serializable

data class RequestPostReportData(
    val userId: String = com.fork.spoonfeed.data.UserData.id.toString(),
    val reason: String,
) : Serializable