package com.fork.spoonfeed.data.repository

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
    ): ResponseLoginWithKakaoData {
        return authDataSource.loginWithKakao(accessToken, refreshToken)
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

    override fun setAutoLoginPlatformManager(value: String?) {
        authDataSource.setAutoLoginPlatformManager(value)
    }

    override fun getAutoLoginPlatformManager(): String? {
        return authDataSource.getAutoLoginPlatformManager()
    }
}

