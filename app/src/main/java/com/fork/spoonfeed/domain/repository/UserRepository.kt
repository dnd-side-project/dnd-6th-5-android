package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData

interface UserRepository {
    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData
    suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData
}