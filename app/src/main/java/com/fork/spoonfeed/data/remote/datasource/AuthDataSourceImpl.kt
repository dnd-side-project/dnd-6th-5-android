package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData

class AuthDataSourceImpl(private val authService: AuthService) : AuthDataSource {

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
}

