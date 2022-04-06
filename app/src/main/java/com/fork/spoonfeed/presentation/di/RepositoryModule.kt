package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.local.dao.ReportPostDao
import com.fork.spoonfeed.data.remote.datasource.*
import com.fork.spoonfeed.data.repository.*
import com.fork.spoonfeed.domain.repository.*
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

    @Provides
    @Singleton
    fun provideQuestionRepository(questionDataSource: QuestionDataSource): QuestionRepository {
        return QuestionRepositoryImpl(questionDataSource)
    }
}
