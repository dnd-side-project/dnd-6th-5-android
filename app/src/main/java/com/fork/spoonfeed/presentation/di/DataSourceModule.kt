package com.fork.spoonfeed.presentation.di

import android.content.Context
import com.fork.spoonfeed.data.local.AutoLoginPlatformManager
import com.fork.spoonfeed.data.remote.api.auth.AuthService
import com.fork.spoonfeed.data.remote.api.community.CommentService
import com.fork.spoonfeed.data.remote.api.community.PostService
import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.api.user.QuestionService
import com.fork.spoonfeed.data.remote.datasource.AuthDataSource
import com.fork.spoonfeed.data.remote.datasource.AuthDataSourceImpl
import com.fork.spoonfeed.data.remote.datasource.CommentDataSource
import com.fork.spoonfeed.data.remote.datasource.CommentDataSourceImpl
import com.fork.spoonfeed.data.remote.datasource.PolicyDataSource
import com.fork.spoonfeed.data.remote.datasource.PolicyDataSourceImpl
import com.fork.spoonfeed.data.remote.datasource.PostDataSource
import com.fork.spoonfeed.data.remote.datasource.PostDataSourceImpl
import com.fork.spoonfeed.data.remote.api.user.UserService
import com.fork.spoonfeed.data.remote.datasource.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAutoLoginPlatformManager(@ApplicationContext context: Context): AutoLoginPlatformManager {
        return AutoLoginPlatformManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(
        authService: AuthService,
        autoLoginPlatformManager: AutoLoginPlatformManager
    ): AuthDataSource {
        return AuthDataSourceImpl(authService, autoLoginPlatformManager)
    }

    @Provides
    @Singleton
    fun providePolicyDataSource(policyService: PolicyService): PolicyDataSource {
        return PolicyDataSourceImpl(policyService)
    }

    @Provides
    @Singleton
    fun providePostDataSource(postService: PostService): PostDataSource {
        return PostDataSourceImpl(postService)
    }

    @Provides
    @Singleton
    fun provideCommentDataSource(commentService: CommentService): CommentDataSource {
        return CommentDataSourceImpl(commentService)
    }

    @Provides
    @Singleton
    fun provideUserDataSource(
        userService: UserService
    ): UserDataSource {
        return UserDataSourceImpl(userService)
    }

    @Provides
    @Singleton
    fun provideQuestioinDataSource(questionService: QuestionService): QuestionDataSource {
        return QuestionDataSourceImpl(questionService)
    }
}
