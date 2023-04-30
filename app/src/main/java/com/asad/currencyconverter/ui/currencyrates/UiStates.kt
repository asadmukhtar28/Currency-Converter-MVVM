package com.asad.currencyconverter.ui.currencyrates


sealed class UiStates

object LoadingState : UiStates()
object ErrorState : UiStates()
object ContentState : UiStates()