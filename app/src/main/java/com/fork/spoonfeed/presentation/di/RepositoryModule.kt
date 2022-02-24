package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.remote.datasource.AuthDataSource
import com.fork.spoonfeed.data.remote.datasource.CommentDataSource
import com.fork.spoonfeed.data.remote.datasource.PolicyDataSource
import com.fork.spoonfeed.data.remote.datasource.PostDataSource
import com.fork.spoonfeed.data.remote.datasource.UserDataSource
import com.fork.spoonfeed.data.repository.AuthRepositoryImpl
import com.fork.spoonfeed.data.repository.CommentRepositoryImpl
import com.fork.spoonfeed.data.repository.PolicyRepositoryImpl
import com.fork.spoonfeed.data.repository.PostRepositoryImpl
import com.fork.spoonfeed.data.repository.UserRepositoryImpl
import com.fork.spoonfeed.domain.repository.AuthRepository
import com.fork.spoonfeed.domain.repository.CommentRepository
import com.fork.spoonfeed.domain.repository.PolicyRepository
import com.fork.spoonfeed.domain.repository.PostRepository
import com.fork.spoonfeed.domain.repository.UserRepository
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
    fun provideAuthRepository(authDataSource: AuthDataSource): AuthRepository {
        return AuthRepositoryImpl(authDataSource)
    }

    @Provides
    @Singleton
    fun providePolicyRepository(policyDataSource: PolicyDataSource): PolicyRepository {
        return PolicyRepositoryImpl(policyDataSource)
    }

    @Provides
    @Singleton
    fun providePostRepository(postDataSource: PostDataSource): PostRepository {
        return PostRepositoryImpl(postDataSource)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(commentDataSource: CommentDataSource): CommentRepository {
        return CommentRepositoryImpl(commentDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserRepositoryImpl(userDataSource)
    }
}
