# Currency-Converter-MVVM

Currency-Converter-MVVM is build
with [Exchange Rates](https://apilayer.com/marketplace/exchangerates_data-api?utm_source=apilayermarketplace&utm_medium=featured)
api . This repo used 100% Kotlin with MVVM architecture and modern android development techniques
such as Kotlin-Coroutines, Kotlin-flows, Room, Work manager.

Requirements
------------
To try out this repo, you need to use [Android Studio](https://developer.android.com/studio)
. You can clone this repository or import the project from Android Studio following the steps
[here](https://developer.android.com/jetpack/compose/setup#sample).


Things you need to setup
------------

- Built with production and debug product flavours.
- Define these two keys in local.properties before run the build.
  - CURRENCY_EXCHANGE_API_KEY
  - LIVE_CURRENCY_EXCHANGE_API_KEY

Feature
------------

- Persist Exchange rates on local database using Room.
- User can enter the amount to be converted to other currencies by clicking on the "Convert".
- Exchange rates fetch
  from [Exchange Rates](https://apilayer.com/marketplace/exchangerates_data-api?utm_source=apilayermarketplace&utm_medium=featured)
  after 24 hours.

Sample
------------



