package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserUserLikePolicyData

interface UserRepository {

    suspend fun getUserData(): ResponseUserData

    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData

    suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData

    suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData

    suspend fun getUserLikePolicy(accessToken: String, platform: String, id: Int): ResponseUserUserLikePolicyData
}
