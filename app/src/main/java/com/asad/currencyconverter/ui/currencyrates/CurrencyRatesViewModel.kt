package com.asad.currencyconverter.ui.currencyrates

import androidx.lifecycle.viewModelScope
import com.asad.currencyconverter.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(val repository: CurrencyRatesRepository) :
    BaseViewModel(repository.dataManager) {

    fun fetchCurrencySymbols() = viewModelScope.launch {
        repository.fetchCurrencySymbols().collect {
        }
    }
}