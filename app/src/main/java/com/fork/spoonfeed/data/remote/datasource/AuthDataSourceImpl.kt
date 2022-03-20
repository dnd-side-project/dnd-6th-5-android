package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.local.AutoLoginPlatformManager
import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.model.auth.*
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val autoLoginPlatformManager: AutoLoginPlatformManager
) : AuthDataSource {

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
    ): ResponseDeleteWithKakaoData {
        return authService.deleteWithKakao(accessToken)
    }

    override suspend fun deleteWithNaver(
        accessToken: String
    ): ResponseDeleteWithNaverData {
        return authService.deleteWithNaver(accessToken)
    }

    override fun setAutoLoginPlatformManager(value: String?) {
        autoLoginPlatformManager.setPlatform(value)
    }

    override fun getAutoLoginPlatformManager(): String? {
        return autoLoginPlatformManager.getPlatform()
    }
}

