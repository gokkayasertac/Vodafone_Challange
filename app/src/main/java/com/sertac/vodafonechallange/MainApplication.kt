package com.sertac.vodafonechallange

import android.app.Application
import com.sertac.core.data.UserProfileRepository
import com.sertac.core.interactors.AddUserProfile
import com.sertac.core.interactors.GetUserProfile
import com.sertac.core.interactors.RemoveUserProfile
import com.sertac.vodafonechallange.framework.Interactors
import com.sertac.vodafonechallange.framework.MainViewModelFactory
import com.sertac.vodafonechallange.framework.RoomUserProfileDataSource

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MainViewModelFactory.inject(this)
    }
}