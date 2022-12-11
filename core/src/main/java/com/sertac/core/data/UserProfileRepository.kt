package com.sertac.core.data

import com.sertac.core.domain.userprofile.UserProfileResponse

class UserProfileRepository(private val userProfileDataSource: UserProfileDataSource) {

    suspend fun addUser(user: UserProfileResponse) = userProfileDataSource.add(user)

    suspend fun getUsers() = userProfileDataSource.readAll()

    suspend fun removeUser(user: UserProfileResponse) = userProfileDataSource.remove(user)
}