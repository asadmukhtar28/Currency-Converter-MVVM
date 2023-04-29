package com.asad.currencyconverter.ui.currencyrates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.asad.currencyconverter.R
import com.asad.currencyconverter.ui.theme.CurrencyConverterTheme

class CurrencyRatesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color(0xff1A1C1E)
                ) {
                    CurrencyConverter()
                }
            }
        }
    }
}

@Composable
fun CurrencyConverter() {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderSection(modifier = Modifier.fillMaxWidth())
        CurrenciesList(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
        )
    }
}

@Composable
private fun CurrenciesList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(10) {
            ItemCurrency()
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ItemCurrency() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "United Arab Emirates",
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "AED", fontSize = TextUnit(20f, TextUnitType.Sp), color = Color.Black
            )
        }

        Text(text = "1.065", fontSize = TextUnit(20f, TextUnitType.Sp))
    }
}

//@Preview
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
            value = "456",
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