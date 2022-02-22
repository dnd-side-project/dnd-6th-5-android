package com.fork.spoonfeed.data.repository

import com.fork.spoonfeed.data.remote.datasource.AuthDataSource
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithNaverData {
        return authDataSource.loginWithNaver(accessToken, refreshToken)
    }
}
