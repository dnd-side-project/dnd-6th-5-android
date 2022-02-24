package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {

    override suspend fun getUserData(): ResponseUserData {
        return userService.getUserData()
    }

    override suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData {
        return userService.patchUserNickName(accessToken, platform, requestUserNickNameData)
    }
}
