package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData

interface AuthRepository {

    suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithNaverData
}
