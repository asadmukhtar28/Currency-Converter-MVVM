package com.asad.currencyconverter.ui.currencyrates

import androidx.lifecycle.viewModelScope
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(private val repository: CurrencyRatesRepository) :
    BaseViewModel(repository.dataManager) {

    private val _uiState = MutableStateFlow<UiStates>(LoadingState)
    val uiState: StateFlow<UiStates> = _uiState

    private val _currencyRatesList = MutableStateFlow<List<CurrencyRatesDbModel>>(arrayListOf())
    val currencyRatesList: StateFlow<List<CurrencyRatesDbModel>> = _currencyRatesList


    init {
        fetchCurrencySymbols()
    }

    private fun fetchCurrencySymbols() = viewModelScope.launch {
        repository.fetchCurrencyRatesFromDb().collect {
            if (it.isNotEmpty()) {
                delay(1000)
                _uiState.value = ContentState
                _currencyRatesList.value = it
            } else {
                _uiState.value = ErrorState
            }
        }
    }
}