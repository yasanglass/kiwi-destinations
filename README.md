# Kiwi Destinations

A simple sample app to find the best offers of the day from [kiwi.com](https://www.kiwi.com/).

## Features

- Shows 5 offers for the day
- Offers are only updated when the day changes
- Offers are cached locally until the day changes
- Material YOU support
- Full day/night theme support
- Several different types of tests
  
## Technologies & Libraries

- Kotlin + Coroutines + Flow
- MVVM + Clean Architecture
- Hilt for Dependency Injection
- Jetpack Compose for the UI (single activity & no layout xml files)
- Room Persistence to cache the data
- Retrofit + Moshi for networking
- Coil for image loading
- [GitHub Actions](https://github.com/yasandev/kiwi-destinations/actions) for automatically running tests & building APKs
