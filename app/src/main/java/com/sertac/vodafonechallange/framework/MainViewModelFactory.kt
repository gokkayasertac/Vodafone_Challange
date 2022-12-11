package com.sertac.vodafonechallange.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object MainViewModelFactory : ViewModelProvider.Factory {
    lateinit var application: Application

    fun inject(application: Application) {
        MainViewModelFactory.application = application
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (MainViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java)
                .newInstance(
                    application
                )
        } else {
            throw java.lang.IllegalStateException("ViewModel must extend MainViewModel")
        }
    }
}