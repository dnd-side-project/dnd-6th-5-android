package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.user.RequestPatchUserFilterData
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponsePatchUserFilterData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNameDuplicate
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData

interface UserDataSource {

    suspend fun getUserData(): ResponseUserData

    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData

    suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData

    suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData

    suspend fun getUserLikePolicy(): ResponseUserLikePolicyData

    suspend fun patchUserFilter(body: RequestPatchUserFilterData): ResponsePatchUserFilterData

    suspend fun checkUserNameDuplicate(nickName: String): ResponseUserNameDuplicate
}
