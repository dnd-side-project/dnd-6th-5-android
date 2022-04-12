package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.UserDataSource
import com.fork.spoonfeed.data.remote.model.user.*
import com.fork.spoonfeed.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun blockUser(blockedId: Int): ResponseBlockUserData {
        return userDataSource.blockUser(blockedId = blockedId)
    }

    override suspend fun getUserData(): ResponseUserData {
        return userDataSource.getUserData()
    }

    override suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData {
        return userDataSource.patchUserNickName(accessToken, platform, requestUserNickNameData)
    }

    override suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData {
        return userDataSource.getUserPost(accessToken, platform, userId)
    }

    override suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData {
        return userDataSource.getUserComment(accessToken, platform, userId)
    }

    override suspend fun getUserLikePolicy(): ResponseUserLikePolicyData {
        return userDataSource.getUserLikePolicy()
    }

    override suspend fun patchUserFilter(body: RequestPatchUserFilterData): ResponsePatchUserFilterData {
        return userDataSource.patchUserFilter(body = body)
    }

    override suspend fun checkUserNameDuplicate(nickName: String): ResponseUserNameDuplicate {
        return userDataSource.checkUserNameDuplicate(nickName)
    }
}
