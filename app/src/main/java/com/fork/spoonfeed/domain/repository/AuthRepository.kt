package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.auth.*

interface AuthRepository {

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

    fun setAutoLoginPlatformManager(value: String?)

    fun getAutoLoginPlatformManager(): String?
}

