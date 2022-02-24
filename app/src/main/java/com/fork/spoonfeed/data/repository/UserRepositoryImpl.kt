package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.datasource.UserDataSource
import com.fork.spoonfeed.data.remote.model.user.*
import com.fork.spoonfeed.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData {
        return userDataSource.patchUserNickName(accessToken, platform, requestUserNickNameData)
    }

    override suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData {
        return userDataSource.getUserPost(accessToken, platform, userId)
    }

    override suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData {
        return userDataSource.getUserComment(accessToken, platform, userId)
    }

    override suspend fun getUserLikePolicy(accessToken: String, platform: String, userId: Int): ResponseUserUserLikePolicyData {
        return userDataSource.getUserLikePolicy(accessToken, platform, userId)
    }
}