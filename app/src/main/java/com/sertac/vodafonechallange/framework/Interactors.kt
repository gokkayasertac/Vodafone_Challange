package com.sertac.vodafonechallange.framework

import com.sertac.core.interactors.*

data class Interactors(
    val addUserProfile: AddUserProfile,
    val getUserProfile: GetUserProfile,
    val removeUserProfile: RemoveUserProfile
)