package com.fork.spoonfeed.data.remote.api.auth

import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.remote.model.auth.*
import retrofit2.Response
import retrofit2.http.DELETE
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
    ): Response<ResponseLoginWithKakaoData>

    @GET("logout/naver")
    suspend fun logoutWithNaver(
        @Header("access_token") accessToken: String,
    ): ResponseLogoutWithNaverData

    @GET("logout/kakao")
    suspend fun logoutWithKakao(
        @Header("access_token") accessToken: String,
    ): ResponseLogoutWithKakaoData

    @DELETE("user/kakao")
    suspend fun deleteWithKakao(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
    ): ResponseDeleteWithKakaoData

    @DELETE("user/naver")
    suspend fun deleteWithNaver(
        @Header("access_token") accessToken: String = UserData.accessToken!!,
    ): ResponseDeleteWithNaverData

    @GET("token")
    suspend fun getToken(
        @Header("refresh_token") refreshToken: String,
        @Header("platform") platform: String
    ): Response<ResponseTokenData>
}
