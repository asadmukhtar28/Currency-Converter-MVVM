package com.asad.currencyconverter.ui.currencyrates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.currencyconverter.CurrencyConverterApp
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.utils.convertDoubleTo2Decimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyRatesViewModel @Inject constructor(private val repository: CurrencyRatesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiStates>(LoadingState)
    val uiState: StateFlow<UiStates> = _uiState

    private val _currencyRatesList = MutableStateFlow<List<CurrencyRatesDbModel>>(arrayListOf())
    val currencyRatesList: StateFlow<List<CurrencyRatesDbModel>> = _currencyRatesList

    private val _selectedCurrency = MutableStateFlow(CurrencyRatesDbModel())
    val selectedCurrency: StateFlow<CurrencyRatesDbModel> = _selectedCurrency

    init {
        fetchCurrencySymbols()
    }

    private fun fetchCurrencySymbols() = viewModelScope.launch {
        repository.fetchCurrencyRatesFromDb().collect {
            if (it.isNotEmpty()) {
                _uiState.value = ContentState
                _currencyRatesList.value = it
                _selectedCurrency.value = _currencyRatesList.value.first()
            } else {
                if (CurrencyConverterApp.isNetworkConnected) {
                    _uiState.value = LoadingState
                } else _uiState.value = ErrorState
            }
        }
    }

    fun performConversion(amount: Double) {
        viewModelScope.launch(Dispatchers.Default) {
            val list: MutableList<CurrencyRatesDbModel> = ArrayList()
            currencyRatesList.value.let { listItems ->
                if (listItems.isNotEmpty()) {
                    val currencyValue = amount / listItems.filter {
                        selectedCurrency.value.currencyName == it.currencyName
                    }.map {
                        it.currencyRate
                    }.first()

                    listItems.forEach {
                        list.add(
                            CurrencyRatesDbModel(
                                currencyName = it.currencyName,
                                currencyRate = (it.currencyRate * currencyValue).convertDoubleTo2Decimal()
                            )
                        )
                    }
                }
            }

            _currencyRatesList.value = list
        }

    }

    fun updateSelectedCurrency(currencyRatesModel: CurrencyRatesDbModel) {
        _selectedCurrency.value = currencyRatesModel
    }
}