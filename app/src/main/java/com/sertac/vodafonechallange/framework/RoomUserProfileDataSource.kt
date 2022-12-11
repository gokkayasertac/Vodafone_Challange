package com.sertac.vodafonechallange.framework

import android.content.Context
import com.sertac.core.data.UserProfileDataSource
import com.sertac.core.domain.userprofile.UserProfileResponse
import com.sertac.vodafonechallange.framework.db.MainDatabase
import com.sertac.vodafonechallange.framework.db.UserProfileTypeConverter

class RoomUserProfileDataSource(context: Context) : UserProfileDataSource {

    private val userProfileDao = MainDatabase.getInstance(context = context).userProfileDao()

    override suspend fun add(user: UserProfileResponse) {
        userProfileDao.addUserProfile(UserProfileTypeConverter().modelToEntity(user))
    }

    override suspend fun readAll(): UserProfileResponse {
        return UserProfileTypeConverter().entityToModel(userProfileDao.getUserProfile())

    }

    override suspend fun remove(user: UserProfileResponse) {
        userProfileDao.removeUserProfile(UserProfileTypeConverter().modelToEntity(user))
    }
}