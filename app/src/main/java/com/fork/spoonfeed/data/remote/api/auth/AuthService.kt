package com.fork.spoonfeed.data.remote.api.auth

import com.fork.spoonfeed.data.remote.model.auth.ResponseLoginWithNaverData
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {

    @GET("login/naver")
    suspend fun loginWithNaver(
        @Header("access_token") accessToken: String,
        @Header("refresh_token") refreshToken: String
    ): ResponseLoginWithNaverData
}
