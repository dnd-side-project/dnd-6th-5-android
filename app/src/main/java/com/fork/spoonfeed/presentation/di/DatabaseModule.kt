package com.fork.spoonfeed.presentation.di

import android.content.Context
import androidx.room.Room
import com.fork.spoonfeed.data.local.dao.CommentReportDao
import com.fork.spoonfeed.data.local.dao.PostReportDao
import com.fork.spoonfeed.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "report record database"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideReportPostDao(database: AppDatabase): PostReportDao =
        database.postReportDao

    @Provides
    fun provideReportCommentDao(database: AppDatabase): CommentReportDao =
        database.commentReportDao
}
