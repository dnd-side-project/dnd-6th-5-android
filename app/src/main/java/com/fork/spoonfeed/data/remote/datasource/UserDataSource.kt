package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.user.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface UserDataSource {
    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData
    suspend fun getUserPost(accessToken: String, platform: String, userId: Int): ResponseUserPostData
    suspend fun getUserComment(accessToken: String, platform: String, userId: Int): ResponseUserCommentData
    suspend fun getUserLikePolicy(accessToken: String, platform: String, id: Int): ResponseUserUserLikePolicyData
}