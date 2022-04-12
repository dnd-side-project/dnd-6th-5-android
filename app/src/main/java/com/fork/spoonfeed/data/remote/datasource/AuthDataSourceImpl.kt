package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.local.AutoLoginManager
import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.model.auth.ResponseDeleteWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseDeleteWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithNaverData
import com.google.gson.Gson
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val autoLoginManager: AutoLoginManager
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
    ): Pair<String, ResponseLoginWithKakaoData> {
        val response = authService.loginWithKakao(accessToken, refreshToken)
        return (response.headers()["access_token"]!! to response.body()!!)
    }

    override suspend fun logoutWithNaver(accessToken: String): ResponseLogoutWithNaverData {
        return authService.logoutWithNaver(accessToken)
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

    override suspend fun getToken(refreshToken: String, platform: String): Pair<String?, String?> {
        val token = authService.getToken(refreshToken, platform)
        return token.headers()["access_token"] to token.headers()["refresh_token"]
    }

    override fun setAutoLoginManager(value: AutoLoginManager.Companion.UserInfo?) {
        val userDataJson = Gson().toJson(value, AutoLoginManager.Companion.UserInfo::class.java)
        autoLoginManager.setPlatform(userDataJson)
    }

    override fun getAutoLoginManager(): AutoLoginManager.Companion.UserInfo? {
        return Gson().fromJson(
            autoLoginManager.getPlatform(),
            AutoLoginManager.Companion.UserInfo::class.java
        )
    }
}

