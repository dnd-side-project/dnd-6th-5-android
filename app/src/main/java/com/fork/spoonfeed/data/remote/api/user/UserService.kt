package com.fork.spoonfeed.data.remote.api.user

import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyDetailData
import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface UserService {
    @PATCH("user/nickname")
    suspend fun patchUserNickName(
        @Header("access_token") accessToken: String,
        @Header("platform") platform: String,
        @Body body: RequestUserNickNameData
    ): ResponseUserNickNameData
}