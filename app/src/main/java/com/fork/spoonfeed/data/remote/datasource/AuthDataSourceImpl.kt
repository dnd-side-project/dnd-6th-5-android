package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithKakaoData
import com.fork.spoonfeed.data.remote.model.user.ResponseDeleteWithKakao
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val authService: AuthService) : AuthDataSource {

    override suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithNaverData> {
        val response = authService.loginWithNaver(accessToken, refreshToken)
        return (response.headers()["access_token"]!! to response.body()!!)
    }

    override suspend fun loginWithKakao(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithKakaoData {
        return authService.loginWithKakao(accessToken, refreshToken)
    }

    override suspend fun logoutWithKakao(
        accessToken: String
    ): ResponseLogoutWithKakaoData {
        return authService.logoutWithKakao(accessToken)
    }

    override suspend fun deleteWithKakao(
        accessToken: String
    ): ResponseDeleteWithKakao {
        return authService.deleteWithKakao(accessToken)
    }
}

