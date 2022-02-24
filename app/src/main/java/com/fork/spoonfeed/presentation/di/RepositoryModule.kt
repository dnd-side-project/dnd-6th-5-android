package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.remote.datasource.AuthDataSource
import com.fork.spoonfeed.data.remote.datasource.PolicyDataSource
import com.fork.spoonfeed.data.remote.datasource.PostDataSource
import com.fork.spoonfeed.data.repository.AuthRepositoryImpl
import com.fork.spoonfeed.data.repository.PolicyRepositoryImpl
import com.fork.spoonfeed.data.repository.PostRepositoryImpl
import com.fork.spoonfeed.domain.repository.AuthRepository
import com.fork.spoonfeed.domain.repository.PolicyRepository
import com.fork.spoonfeed.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthDataSource): AuthRepository{
        return AuthRepositoryImpl(authDataSource)
    }

    @Provides
    @Singleton
    fun providePolicyRepository(policyDataSource: PolicyDataSource): PolicyRepository{
        return PolicyRepositoryImpl(policyDataSource)
    }

    @Provides
    @Singleton
    fun providePostRepository(postDataSource: PostDataSource): PostRepository {
        return PostRepositoryImpl(postDataSource)
    }
}
