package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.auth.*

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
    ): ResponseDeleteWithKakaoData

    suspend fun deleteWithNaver(
        accessToken: String,
    ): ResponseDeleteWithNaverData
}

