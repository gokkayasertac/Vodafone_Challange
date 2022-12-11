package com.sertac.vodafonechallange.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UserProfileEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserProfileTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "main.db"
        private var instance: MainDatabase? = null

        private fun create(context: Context): MainDatabase =
            Room.databaseBuilder(context, MainDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().build()

        fun getInstance(context: Context): MainDatabase =
            (instance ?: create(context)).also { instance = it }

    }

    abstract fun userProfileDao(): UserProfileDao
}