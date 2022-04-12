package com.fork.spoonfeed.data.remote.datasource

import com.fork.spoonfeed.data.local.AutoLoginManager
import com.fork.spoonfeed.data.remote.model.auth.ResponseDeleteWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseDeleteWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithNaverData

interface AuthDataSource {

    suspend fun loginWithNaver(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithNaverData>

    suspend fun loginWithKakao(
        accessToken: String,
        refreshToken: String
    ): Pair<String, ResponseLoginWithKakaoData>

    suspend fun logoutWithNaver(
        accessToken: String,
    ): ResponseLogoutWithNaverData

    suspend fun logoutWithKakao(
        accessToken: String,
    ): ResponseLogoutWithKakaoData

    suspend fun deleteWithKakao(
        accessToken: String,
    ): ResponseDeleteWithKakaoData

    suspend fun deleteWithNaver(
        accessToken: String,
    ): ResponseDeleteWithNaverData

    suspend fun getToken(
        refreshToken: String,
        platform: String
    ): Pair<String?, String?>

    fun setAutoLoginManager(value: AutoLoginManager.Companion.UserInfo?)

    fun getAutoLoginManager(): AutoLoginManager.Companion.UserInfo?
}

