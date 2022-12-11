package com.sertac.core.data

import com.sertac.core.domain.userprofile.UserProfileResponse

interface ShownUserProfileDataSource {

    fun setShownUser(user: UserProfileResponse)

    fun getShownUser(): UserProfileResponse
}