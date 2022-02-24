package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.user.RequestUserNickNameData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserNickNameData


interface UserDataSource {

    suspend fun getUserData(): ResponseUserData

    suspend fun patchUserNickName(accessToken: String, platform: String, requestUserNickNameData: RequestUserNickNameData): ResponseUserNickNameData
}
