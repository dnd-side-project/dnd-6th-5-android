package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData

interface AuthDataSource {

    suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithNaverData
}