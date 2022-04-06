package com.fork.spoonfeed.data.remote.model.policy

import java.io.Serializable

data class RequestReportData(
    val userId: String = com.fork.spoonfeed.data.UserData.id.toString(),
    val reason: String,
) : Serializable