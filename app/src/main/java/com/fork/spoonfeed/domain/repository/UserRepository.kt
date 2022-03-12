package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.user.*

interface UserRepository {

    suspend fun getUserData(): ResponseUserData

    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData

    suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData

    suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData

    suspend fun getUserLikePolicy(): ResponseUserLikePolicyData

    suspend fun patchUserFilter(body: RequestPatchUserFilterData): ResponsePatchUserFilterData
}
