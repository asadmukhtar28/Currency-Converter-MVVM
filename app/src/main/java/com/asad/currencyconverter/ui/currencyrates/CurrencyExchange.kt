package com.asad.currencyconverter.ui.currencyrates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asad.currencyconverter.R
import com.asad.currencyconverter.data.local.models.CurrencyRatesDbModel
import com.asad.currencyconverter.ui.theme.CurrencyConverterTheme

@Composable
fun CurrencyConverter(viewModel: CurrencyRatesViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState()
    val currencyRatesList = viewModel.currencyRatesList.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {

        HeaderSection(modifier = Modifier.fillMaxWidth())

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
                        ), currencyRatesList
                )
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
            painter = painterResource(id = R.drawable.ic_no_result), contentDescription = null,
            modifier = Modifier.size(100.dp),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
    }
}

@Composable
private fun CurrenciesList(
    modifier: Modifier = Modifier, currencySymbolDbModel: List<CurrencyRatesDbModel>
) {
    LazyColumn(modifier = modifier) {
        items(items = currencySymbolDbModel, key = { item ->
            item.id
        }) {
            ItemCurrency(it)
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ItemCurrency(
    currencyRatesDbModel: CurrencyRatesDbModel = CurrencyRatesDbModel(
        currencyName = "AED", currencyRate = 128.0
    )
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_currency),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )

        Text(
            text = currencyRatesDbModel.currencyName,
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

@Preview
@Composable
fun ItemCurrencyPreview() {
    ItemCurrency()
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text(
            text = "Currency Converter", fontSize = TextUnit(30f, TextUnitType.Sp)
        )
        TextField(
            value = "",
            onValueChange = {},
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
                Text(
                    text = "$", color = Color.White, fontSize = TextUnit(25f, TextUnitType.Sp)
                )
            },
            textStyle = TextStyle(fontSize = TextUnit(30f, TextUnitType.Sp))
        )
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