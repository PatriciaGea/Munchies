## Project Goal

Mobile application project practice, scalable Android architecture and modern Kotlin development.

# Munchies

A mobile food delivery app that helps users discover restaurants through filters and view restaurant details.

## Features

* Fetch restaurants from the Umain API
* Display restaurants in a list
* Filter restaurants by multiple tags
* View restaurant details
* Display restaurant information such as rating, delivery time and image
* Load images from the provided API URLs

## Tech Stack

* Kotlin
* Android
* Jetpack Compose
* Retrofit
* Kotlin Serialization
* Hilt
* Coroutines
* MVVM
* Clean Architecture

## Architecture

The project is organized into separate layers:

```text
API
↓
JSON
↓
Retrofit + Serialization
↓
DTO
↓
Mapper
↓
Domain Model
↓
Repository
↓
Use Case
↓
ViewModel
↓
UiState
↓
Jetpack Compose
```

## Project Structure

```text
com.umain.munchies/
├── data/
│   ├── remote/
│   │   ├── api/
│   │   └── dto/
│   ├── mapper/
│   └── repository/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
├── di/
│
├── ui/
│   ├── restaurantlist/
│   └── restaurantdetail/
│
├── navigation/
│
└── core/
```

## API

The application uses the Umain Food Delivery API:

https://food-delivery.umain.io/swagger/

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle.
4. Run the application on an Android emulator or physical device.

## Done By:

Patricia Gea Rodrigues
