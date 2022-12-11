package com.sertac.core.interactors

import com.sertac.core.data.UserProfileRepository

class GetUserProfile(private val repository: UserProfileRepository) {

    suspend operator fun invoke() = repository.getUsers()

}