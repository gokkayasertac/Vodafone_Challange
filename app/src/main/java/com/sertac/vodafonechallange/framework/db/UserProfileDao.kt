package com.sertac.vodafonechallange.framework.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface UserProfileDao {

    @Insert(onConflict = REPLACE)
    suspend fun addUserProfile(userProfile: UserProfileEntity)

    @Query("SELECT * FROM userProfile")
    suspend fun getUserProfile(): UserProfileEntity

    @Delete
    suspend fun removeUserProfile(userProfileEntity: UserProfileEntity)
}