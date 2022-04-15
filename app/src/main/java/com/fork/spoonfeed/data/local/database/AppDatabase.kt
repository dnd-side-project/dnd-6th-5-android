package com.fork.spoonfeed.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fork.spoonfeed.data.local.dao.CommentReportDao
import com.fork.spoonfeed.data.local.dao.PostReportDao
import com.fork.spoonfeed.data.local.entity.CommentReportData
import com.fork.spoonfeed.data.local.entity.PostReportData

@Database(entities = [PostReportData::class, CommentReportData::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val postReportDao: PostReportDao
    abstract val commentReportDao: CommentReportDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "report record database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
