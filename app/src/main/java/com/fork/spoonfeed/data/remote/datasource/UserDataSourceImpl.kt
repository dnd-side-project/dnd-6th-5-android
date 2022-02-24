package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {

    override suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData {
        return userService.patchUserNickName(accessToken, platform, requestUserNickNameData)
    }

    override suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData {
        return userService.getUserPost(accessToken, platform, userId)
    }
}