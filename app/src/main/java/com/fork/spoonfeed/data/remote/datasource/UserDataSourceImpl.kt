package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
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

    override suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData {
        return userService.getUserPost(accessToken, platform, userId)
    }

    override suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData {
        return userService.getUserComment(accessToken, platform, userId)
    }

    override suspend fun getUserLikePolicy(): ResponseUserLikePolicyData {
        return userService.getUserLikePolicy()
    }
}
