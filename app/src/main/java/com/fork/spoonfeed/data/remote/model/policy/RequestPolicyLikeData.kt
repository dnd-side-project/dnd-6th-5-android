package com.fork.spoonfeed.data.remote.model.policy

import java.io.Serializable

data class RequestPolicyLikeData(
    val userId: String = com.fork.spoonfeed.data.UserData.id.toString(),
    val policyId: String,
) : Serializable