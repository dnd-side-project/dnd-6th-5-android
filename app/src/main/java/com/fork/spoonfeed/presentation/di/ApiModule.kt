package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providePolicyService(retrofit: Retrofit): PolicyService {
        return retrofit.create(PolicyService::class.java)
    }
}
