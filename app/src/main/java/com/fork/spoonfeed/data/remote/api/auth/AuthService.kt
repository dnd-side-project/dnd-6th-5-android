package com.fork.spoonfeed.data.remote.api.auth

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithKakaoData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import com.fork.spoonfeed.data.remote.model.auth.ResponseLogoutWithKakaoData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {

    @GET("login/naver")
    suspend fun loginWithNaver(
        @Header("access_token") accessToken: String,
        @Header("refresh_token") refreshToken: String
    ): Response<ResponseLoginWithNaverData>

    @GET("login/kakao")
    suspend fun loginWithKakao(
        @Header("access_token") accessToken: String,
        @Header("refresh_token") refreshToken: String
    ): ResponseLoginWithKakaoData

    @GET("logout/kakao")
    suspend fun logoutWithKakao(
        @Header("access_token") accessToken: String,
    ): ResponseLogoutWithKakaoData
}
