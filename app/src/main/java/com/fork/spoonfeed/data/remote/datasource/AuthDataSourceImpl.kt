package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData

class AuthDataSourceImpl(private val authService: AuthService) : AuthDataSource {

    override suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithNaverData {
        return authService.loginWithNaver(accessToken, refreshToken)
    }

    override suspend fun loginWithKakao(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithKakaoData {
        return authService.loginWithKakao(accessToken, refreshToken)
    }
}
