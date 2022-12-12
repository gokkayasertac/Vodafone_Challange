package com.sertac.vodafonechallange

import android.app.Application
import com.sertac.vodafonechallange.framework.MainViewModelFactory

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MainViewModelFactory.inject(this)
    }
}