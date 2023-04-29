package com.asad.currencyconverter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.currencyconverter.data.manager.AppDataManager
import com.asad.currencyconverter.utils.debugLog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class BaseViewModel(open val dataManager: AppDataManager) : ViewModel() {

    fun makeApiCall(listener: suspend (coroutineScope: CoroutineScope) -> Unit) {
        val handler = CoroutineExceptionHandler { _, exception ->
            debugLog("asad_exc", "exception: ${exception.message}")
            if (exception is TimeoutException || exception is SocketTimeoutException || exception is UnknownHostException || exception is NullPointerException) {
                /*apiStates.value = ApiResponseErrors(
                    ApiErrorHandler(
                        StatusCodes.INTERNET_NOT_AVAILABLE,
                        dataManager.getResourceManager().getString(
                            R.string.internet_error
                        )
                    )
                )*/
            }
        }

        viewModelScope.launch(handler) {
            listener.invoke(this)
        }
    }
}