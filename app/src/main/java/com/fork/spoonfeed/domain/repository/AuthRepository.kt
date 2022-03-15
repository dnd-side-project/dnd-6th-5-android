package com.fork.spoonfeed.domain.repository

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithKakaoData
import com.fork.spoonfeed.data.remote.model.user.ResponseDeleteWithKakao

interface AuthRepository {

    suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithNaverData>

    suspend fun loginWithKakao(
        accessToken: String,
        refreshToken: String
    ): ResponseLoginWithKakaoData

    suspend fun logoutWithKakao(
        accessToken: String,
    ): ResponseLogoutWithKakaoData

    suspend fun deleteWithKakao(
        accessToken: String,
    ): ResponseDeleteWithKakao
}

