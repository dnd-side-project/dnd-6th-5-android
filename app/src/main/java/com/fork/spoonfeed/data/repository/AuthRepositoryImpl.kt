package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.local.AutoLoginManager
import com.fork.spoonfeed.data.remote.datasource.AuthDataSource
import com.fork.spoonfeed.data.remote.model.auth.*
import com.fork.spoonfeed.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithNaverData> {
        return authDataSource.loginWithNaver(accessToken, refreshToken)
    }

    override suspend fun loginWithKakao(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithKakaoData> {
        return authDataSource.loginWithKakao(accessToken, refreshToken)
    }

    override suspend fun logoutWithNaver(accessToken: String): ResponseLogoutWithNaverData {
        return authDataSource.logoutWithNaver(accessToken)
    }

    override suspend fun logoutWithKakao(
        accessToken: String,
    ): ResponseLogoutWithKakaoData {
        return authDataSource.logoutWithKakao(accessToken)
    }

    override suspend fun deleteWithKakao(
        accessToken: String
    ): ResponseDeleteWithKakaoData {
        return authDataSource.deleteWithKakao(accessToken)
    }

    override suspend fun deleteWithNaver(
        accessToken: String
    ): ResponseDeleteWithNaverData {
        return authDataSource.deleteWithNaver(accessToken)
    }

    override suspend fun getToken(refreshToken: String, platform: String): Pair<String?, String?> {
        return authDataSource.getToken(refreshToken, platform)
    }

    override fun setAutoLoginManager(value: AutoLoginManager.Companion.UserInfo?) {
        authDataSource.setAutoLoginManager(value)
    }

    override fun getAutoLoginManager(): AutoLoginManager.Companion.UserInfo? {
        return authDataSource.getAutoLoginManager()
    }
}

