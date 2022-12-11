package com.sertac.core.data

import com.sertac.core.domain.userprofile.UserProfileResponse

interface UserProfileDataSource {
    suspend fun add(user: UserProfileResponse)

    suspend fun readAll(): UserProfileResponse

    suspend fun remove(user: UserProfileResponse)

}