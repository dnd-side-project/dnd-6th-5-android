package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.user.*
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserDataSource {

    suspend fun getUserData(): ResponseUserData

    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData

    suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData

    suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData

    suspend fun getUserLikePolicy(): ResponseUserLikePolicyData

    suspend fun patchUserFilter(body: RequestPatchUserFilterData): ResponsePatchUserFilterData

    suspend fun checkUserNameDuplicate(nickName: String): ResponseUserNameDuplicate

    suspend fun blockUser(blockedId: Int): ResponseBlockUserData
}
