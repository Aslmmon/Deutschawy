package com.almany.basemodule.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class BaseViewModel() : ViewModel() {

    val _ErrorMessage = MutableLiveData<String>()
    val ErrorMessage: LiveData<String>
        get() = _ErrorMessage

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun <T> getData(
        function: suspend CoroutineScope.() -> T, dataObserver: MutableLiveData<T>,
        loadingObserver: MutableLiveData<Boolean>? = null,
        errorObserver: MutableLiveData<String>? = null,
        finallyBlock: (suspend CoroutineScope.() -> Unit)? = null
    ) {
        if (loadingObserver != null) {
            loadingObserver.value = true
        }
        this.viewModelScope.launch {

            try {
                dataObserver.value = function()
            } catch (e: Throwable) {
                val errorReturned = catchError(e)
                if (errorObserver != null) {
                    errorObserver.value = errorReturned
                } else {
                    _ErrorMessage.value = errorReturned
                }
            } finally {
                if (loadingObserver != null) {
                    loadingObserver.value = false
                } else {
                    _isLoading.postValue(false)
                }
                finallyBlock?.invoke(this)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun <T, Y> getZippedData(
        firstFunction: suspend CoroutineScope.() -> T,
        secondFunction: suspend CoroutineScope.() -> Y,
        firstObserver: MutableLiveData<T>,
        secondObserver: MutableLiveData<Y>,
        finallyBlock: (suspend CoroutineScope.() -> Unit)? = null
    ) {
        this.viewModelScope.launch {
            try {
                val first = flowOf(firstFunction())
                val second = flowOf(secondFunction())
                first.zip(second) { firstData, secondData ->
                    firstObserver.value = firstData
                    secondObserver.value = secondData
                }.collect()
            } catch (e: Throwable) {
            } finally {
                _isLoading.value = false
                finallyBlock?.invoke(this)
            }
        }
    }

    private fun catchError(throwable: Throwable): String {
        throwable.printStackTrace()
        return when (throwable) {
            is IOException -> "NetworkError"
//            is UnknownHostException -> PreferenceKeys.FAIL_NO_INTERNET
//            is TimeoutException -> PreferenceKeys.FAIL_TIME_OUT
            is HttpException -> {
                val code = throwable.code()
                catchCodes(code, throwable)
            }
            else -> "${throwable.message}"
        }
    }

    private fun catchCodes(code: Int, throwable: HttpException): String {
        return when (code) {
            400 -> getError400Message(throwable)
            404 ->  "Not Found"
            403 -> "Forbidden"
            408 -> "Request Time Out"
            409 -> "Conflict"
            422 ->  "Error needed To be converted"
            410 -> "Gone"
            500 -> throwable.response()?.headers()?.get("application-error-message").toString()
            503 -> "GateWay timeout"
            else -> "Unknown Error"
        }
    }

    private fun getError400Message(throwable: HttpException): String {
        var errorMessage = "400 Unknown Error"
        val reponse = throwable.response()

        val errorBody = reponse?.errorBody()
        if (errorBody != null) {
            errorMessage = errorBody.string()
        } else {
            errorMessage = reponse?.message().toString()
        }

        return errorMessage

    }


}