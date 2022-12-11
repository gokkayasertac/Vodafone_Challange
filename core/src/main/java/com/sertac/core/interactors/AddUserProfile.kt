package com.sertac.core.interactors

import com.sertac.core.data.UserProfileRepository
import com.sertac.core.domain.userprofile.UserProfileResponse

class AddUserProfile(private val repository: UserProfileRepository) {

    suspend operator fun invoke(userProfileResponse: UserProfileResponse) = repository.addUser(userProfileResponse)

}