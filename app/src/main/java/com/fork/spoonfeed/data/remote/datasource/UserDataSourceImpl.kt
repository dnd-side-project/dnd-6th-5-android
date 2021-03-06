package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.model.user.*
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserDataSource {
    override suspend fun blockUser(blockedId: Int): ResponseBlockUserData {
        return userService.blockUser(blockedId = blockedId)
    }

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

    override suspend fun patchUserFilter(body: RequestPatchUserFilterData): ResponsePatchUserFilterData {
        return userService.patchUserFilter(body = body)
    }

    override suspend fun checkUserNameDuplicate(nickName: String): ResponseUserNameDuplicate {
        return userService.checkUserNameDuplicate(nickName)
    }
}
