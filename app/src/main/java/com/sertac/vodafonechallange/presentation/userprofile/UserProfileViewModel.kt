package com.sertac.vodafonechallange.presentation.userprofile

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sertac.core.domain.api.ApiService
import com.sertac.core.domain.userprofile.UserProfileResponse
import com.sertac.core.domain.userrepository.UserRepository
import com.sertac.vodafonechallange.framework.MainViewModel
import com.sertac.vodafonechallange.presentation.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileViewModel : MainViewModel() {

    private val showLoadingUserProfile = MutableLiveData(View.GONE)
    val showLoadingUserProfileLiveData: LiveData<Int> = showLoadingUserProfile

    private val showLoadingUserRepo = MutableLiveData(View.GONE)
    val showLoadingUserRepoLiveData: LiveData<Int> = showLoadingUserRepo

    private val userProfile = MutableLiveData<UserProfileResponse>()
    val userProfileLiveData: LiveData<UserProfileResponse> = userProfile

    private val userRepositories = MutableLiveData<List<UserRepository?>?>()
    val userRepositoriesLiveData: LiveData<List<UserRepository?>?> = userRepositories


    fun getUserProfileData(userName: String){
        showLoadingUserProfile.value = View.VISIBLE
        viewModelLaunch {
            val apiService = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(ApiService::class.java)
            val result = apiService.getUser(userName)

            checkResult(result){
                showLoadingUserProfile.value = View.GONE
                userProfile.value = it
            }
        }
    }
    fun getUserReposWithName(userName: String){
        showLoadingUserRepo.value = View.VISIBLE
        viewModelLaunch {
            val apiService = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(ApiService::class.java)
            val result = apiService.getUserRepositories(userName)

            checkResult(result){
                showLoadingUserRepo.value = View.GONE
                userRepositories.value = it
            }
        }
    }

}