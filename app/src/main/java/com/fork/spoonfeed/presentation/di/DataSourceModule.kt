package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.datasource.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthDataSource(authService: AuthService): AuthDataSource {
        return AuthDataSourceImpl(authService)
    }

    @Provides
    @Singleton
    fun providePolicyDataSource(policyService: PolicyService): PolicyDataSource {
        return PolicyDataSourceImpl(policyService)
    }

    @Provides
    @Singleton
    fun provideUserDataSource(userService: UserService): UserDataSource {
        return UserDataSourceImpl(userService)
    }
}
