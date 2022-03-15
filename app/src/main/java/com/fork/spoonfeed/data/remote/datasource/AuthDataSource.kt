package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithKakaoData
import com.fork.spoonfeed.data.remote.model.user.ResponseDeleteWithKakao
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthDataSource {

    suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithNaverData>

    suspend fun loginWithKakao(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithKakaoData

    suspend fun logoutWithKakao(
        accessToken: String,
    ): ResponseLogoutWithKakaoData

    suspend fun deleteWithKakao(
        accessToken: String,
    ): ResponseDeleteWithKakao
}

