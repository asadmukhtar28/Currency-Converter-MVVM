package com.asad.currencyconverter.ui.currencyrates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asad.currencyconverter.R
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.ui.ContentState
import com.asad.currencyconverter.ui.ErrorState
import com.asad.currencyconverter.ui.LoadingState
import com.asad.currencyconverter.ui.MainActivityViewModel
import com.asad.currencyconverter.ui.theme.CurrencyConverterTheme
import kotlinx.coroutines.launch

@Composable
fun CurrencyConverter(viewModel: MainActivityViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val currencyRatesList = viewModel.originalCurrencyRatesList.collectAsState().value
    val conversionCurrencyRatesList = viewModel.conversionCurrencyRatesList.collectAsState().value
    val selectedCurrency = viewModel.selectedCurrency.collectAsState().value

    var amount by rememberSaveable {
        mutableStateOf(0.toDouble())
    }

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxWidth(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { data ->
                    Snackbar(
                        modifier = Modifier.padding(16.dp),
                        content = { Text(data.message) },
                        action = {
                            data.actionLabel?.let { label ->
                                TextButton(onClick = { data.performAction() }) {
                                    Text(label)
                                }
                            }
                        }
                    )
                }
            )
        },
        topBar = {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Black)
            ) {
                if (it.isNotEmpty()) {
                    amount = it.toDouble()
                    viewModel.performConversion(amount)
                } else {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("Please Enter amount")
                    }
                }

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.value) {
                is LoadingState -> {
                    LoadingState(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                            )
                    )
                }

                is ContentState -> {
                    CurrenciesList(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                            ),
                        selectedCurrency,
                        conversionCurrencyRatesList.ifEmpty { currencyRatesList }
                    ) { currencyRatesModel ->
                        viewModel.updateSelectedCurrency(currencyRatesModel)
                    }
                }

                is ErrorState -> {
                    ErrorState(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                            )
                    )
                }
            }
        }

    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_result),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
    }
}

@Composable
private fun CurrenciesList(
    modifier: Modifier = Modifier,
    selectedCurrency: CurrencyRatesDbModel,
    currencySymbolDbModel: List<CurrencyRatesDbModel>,
    onItemClick: (currencyRatesModel: CurrencyRatesDbModel) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = currencySymbolDbModel) {
            ItemCurrency(
                selectedCurrency, it
            ) { currencySymbolDbModel ->
                onItemClick.invoke(currencySymbolDbModel)
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ItemCurrency(
    selectedCurrency: CurrencyRatesDbModel,
    currencyRatesDbModel: CurrencyRatesDbModel = CurrencyRatesDbModel(
        currencyName = "AED", currencyRate = 128.0
    ),
    onItemClick: (currencyRatesModel: CurrencyRatesDbModel) -> Unit
) {
    val currencyIcon by rememberSaveable {
        mutableStateOf(R.drawable.ic_currency)
    }

    val currencyName by rememberSaveable {
        mutableStateOf(currencyRatesDbModel.currencyName)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke(currencyRatesDbModel) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = if (selectedCurrency.currencyName == currencyRatesDbModel.currencyName) R.drawable.ic_selected else R.drawable.ic_unselected),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = currencyIcon),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Text(
                text = currencyName,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = currencyRatesDbModel.currencyRate.toString(),
                fontSize = TextUnit(20f, TextUnitType.Sp)
            )
        }
    }
}

@Preview
@Composable
fun ItemCurrencyPreview() {
    /*ItemCurrency(
        selectedCurrency = CurrencyRatesDbModel(),
        currencyRatesDbModel = CurrencyRatesDbModel(),
        if (selectedCurrency == it) R.drawable.ic_selected else R.drawable.ic_unselected
    ) {}*/
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun HeaderSection(
    modifier: Modifier = Modifier, onAmountValueChange: (amount: String) -> Unit
) {
    var amount by rememberSaveable {
        mutableStateOf("")
    }

    Row(modifier = modifier) {
        TextField(
            value = amount,
            onValueChange = {
                amount = it
            },
            label = {
                Text(
                    text = "Enter Value",
                    style = TextStyle(fontSize = TextUnit(30f, TextUnitType.Sp)),
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 1,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_convert),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            },
            trailingIcon = {
                Text(text = "Convert",
                    color = Color.White,
                    fontSize = TextUnit(25f, TextUnitType.Sp),
                    modifier = Modifier.clickable {
                        onAmountValueChange.invoke(amount)
                    })
            },
            textStyle = TextStyle(fontSize = TextUnit(30f, TextUnitType.Sp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CurrencyConverterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color(0xff1A1C1E)
        ) {
            CurrencyConverter()
        }
    }
}