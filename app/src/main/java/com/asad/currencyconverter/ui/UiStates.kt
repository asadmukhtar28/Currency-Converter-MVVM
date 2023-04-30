package com.asad.currencyconverter.ui


sealed class UiStates

object LoadingState : UiStates()
object ErrorState : UiStates()
object ContentState : UiStates()