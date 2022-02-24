package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.datasource.UserDataSource
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData {
        return userDataSource.patchUserNickName(accessToken, platform, requestUserNickNameData)
    }
}