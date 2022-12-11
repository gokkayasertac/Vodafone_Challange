package com.sertac.vodafonechallange.presentation.repositories

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sertac.core.domain.api.ApiService
import com.sertac.core.domain.repository.Repository
import com.sertac.vodafonechallange.framework.MainViewModel
import com.sertac.vodafonechallange.presentation.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesViewModel : MainViewModel() {


    private val showLoadingRepos = MutableLiveData(View.GONE)
    val showLoadingReposLiveData: LiveData<Int> = showLoadingRepos

    private val repositories = MutableLiveData<List<Repository?>?>()
    val repositoriesLiveData: LiveData<List<Repository?>?> = repositories


    fun getRepos(){
        showLoadingRepos.value = View.VISIBLE
        viewModelLaunch {
            val apiService = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(ApiService::class.java)
            val result = apiService.getRepositories()

            checkResult(result){
                showLoadingRepos.value = View.GONE
                repositories.value = it
            }
        }
    }
}