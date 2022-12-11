package com.sertac.vodafonechallange.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sertac.vodafonechallange.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

open class MainViewModel :
    ViewModel() {


    val errorState = SingleLiveEvent<ErrorState>()
    val errorStateLiveData: LiveData<ErrorState> = errorState


    data class ErrorState(
        val message: String? = null
    )

    fun <T> checkResult(response: Response<T>, onSuccess: (T) -> Unit) {
        try {
            if (response.isSuccessful) {
                if (response.body() != null) {
                    onSuccess.invoke(response.body()!!)
                } else {
                    errorState.value = ErrorState("Error")
                }
            } else {
                errorState.value = ErrorState("Error")
            }
        } catch (e: Exception) {
            errorState.value = ErrorState("No data!!")
        }

    }
    fun viewModelLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main, block = block)
    }
}