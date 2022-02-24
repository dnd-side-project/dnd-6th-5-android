package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData

interface UserRepository {

    suspend fun getUserData(): ResponseUserData

    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData
}
