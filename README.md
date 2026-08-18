## Project Goal

A scalable, production-style Android application built with modern Kotlin and Jetpack Compose, following Clean Architecture and industry best practices, developed by Patricia Gea Rodrigues.

## Munchies

A mobile food delivery app built as a technical assessment for Umain. It helps users discover restaurants through multi-select filters and view restaurant details, including live open/closed status.

<img src="https://github.com/PatriciaGea/Munchies/blob/master/app/src/main/res/drawable/Screenshotmunchies.png" height="426"> <img src="https://github.com/PatriciaGea/Munchies/blob/master/app/src/main/res/drawable/Screenshotmunchies2.png" height="426">
<img src="https://github.com/PatriciaGea/Munchies/blob/master/app/src/main/res/drawable/munchies.gif" width="240" height="426">

## Features

- Fetch restaurants from the Umain Food Delivery API
- Display restaurants in a scrollable list (`LazyColumn`)
- Display filter tags in a horizontal, scrollable list (`LazyRow`)
- Filter restaurants by one or more tags at the same time (multi-select)
- Tap a restaurant to open a detail screen
- Show whether a restaurant is currently open or closed
- Display restaurant rating, delivery time and image
- Load all images (restaurant photos, filter icons) directly from the API, no bundled assets except the Umain logo, exported from Figma as instructed
- Light/dark theme, following the device's system setting

## Tech Stack

| Technology | Why |
|---|---|
| **Kotlin** | Official language for Android |
| **Jetpack Compose** | Declarative UI is the current industry standard over the legacy View system |
| **Material 3** | Google's current design system for Compose; enables consistent theming and typography with minimal setup |
| **Hilt** | Google-recommended dependency injection for Android |
| **Retrofit + OkHttp** | Industry-standard REST client with coroutine support |
| **Kotlin Serialization** | Type-safe JSON parsing |
| **Coil 3** | Image loading built for Compose (`AsyncImage`), lighter and more Compose-idiomatic than Glide |
| **Navigation Compose** | Official navigation library for Compose destinations |
| **Coroutines + Flow** | Structured concurrency for network calls and reactive UI state |

## Concepts Practiced

- **Clean Architecture** (data / domain /UI layers, each depending only on inner layers)
- **MVVM** with a clear data flow (`StateFlow<UiState>`)
- **Repository pattern** with an interface in `domain` and its implementation in `data`, so the ViewModel never depends on Retrofit directly
- **Use Cases** as single-purpose
- **DTO → Domain Model mapping**, keeping API response shapes isolated from the rest of the app
- **Dependency Injection** with Hilt modules (`@Module`, `@Binds`, `@Provides`)
- **Concurrent network calls** with `async`/`coroutineScope` (the API has no bulk filter endpoint, so filter details are fetched in parallel by ID)
- **UI state** per screen (loading / success / error) 
- **Design system fidelity**: implementing exact spacing, typography and colors from a Figma file using Figma's MCP integration
- **Deliberate design exceptions**: documenting when a component intentionally breaks from the app's dynamic theme (e.g. a fixed white card background required by the Figma design, even in dark mode) instead of letting it happen by accident

## Architecture

```text
API (JSON)
   ↓
Retrofit + Kotlin Serialization
   ↓
DTO (data/remote/dto)
   ↓
Mapper (data/mapper)
   ↓
Domain Model (domain/model)
   ↓
Repository (domain interface ← data implementation)
   ↓
Use Case (domain/usecase)
   ↓
ViewModel (StateFlow<UiState>)
   ↓
Jetpack Compose UI
```

Each layer only knows about the layer directly below it. The UI layer never references Retrofit, DTOs, or any networking detail — it only observes a `UiState` exposed by the ViewModel.

## Project Structure

```text
com.umain.munchies/
├── data/
│   ├── remote/
│   │   ├── api/          # Retrofit interface
│   │   └── dto/          # Raw API response shapes
│   ├── mapper/            # DTO → Domain model conversion
│   └── repository/        # Repository interface implementation
│
├── domain/
│   ├── model/              # Clean, API-agnostic models
│   ├── repository/         # Repository contracts (interfaces)
│   └── usecase/            # Single-purpose business actions
│
├── di/                      # Hilt modules (network, repository)
│
├── ui/
│   ├── restaurantlist/      # List screen, ViewModel, UiState, components
│   └── restaurantdetail/    # Detail screen, ViewModel, UiState, components
│
├── navigation/               # NavGraph and route definitions
│
└── theme/                    # Colors, typography, Material 3 theme
```

## API

The application consumes Food Delivery API:

- `GET /restaurants` — all restaurants, including filter IDs, rating, delivery time and image
- `GET /filter/{id}` — details for a single filter (name, icon), fetched in parallel per unique filter ID found in the restaurant list
- `GET /open/{id}` — live open/closed status for a restaurant

Swagger docs: https://food-delivery.umain.io/swagger/

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio (Ladybug or newer recommended).
3. Sync Gradle — dependencies are managed through `gradle/libs.versions.toml` (Gradle Version Catalog).
4. Run the app on an Android emulator or physical device (minSdk 24).

## Challenges & Learnings

- **Stale build cache**: a Gradle Daemon stuck with an old incremental state kept producing an outdated APK, so UI changes stopped appearing on the emulator and physical device even though the source code and the Compose Preview were correct. Diagnosed by comparing APK output timestamps against the source file's last edit, then resolved with `gradlew --stop`, clearing `app/build` and `.gradle`, and a full `gradlew assembleDebug --rerun-tasks`. Takeaway: when a UI change doesn't show up but Preview renders it correctly, suspect the build/install pipeline before suspecting the code.

<img src="https://github.com/PatriciaGea/Munchies/blob/master/app/src/main/res/drawable/preview.png" height="400">

- **AGP 9 migration friction**: the new Android Gradle Plugin's built-in Kotlin support initially conflicted with KSP (required by Hilt) and with the traditional `org.jetbrains.kotlin.android` plugin. Resolved by explicitly disabling the new DSL behavior (`android.builtInKotlin=false`, `android.newDsl=false`) while keeping KSP and the standard Kotlin plugin — a good example of dealing with breaking changes in a fast-moving toolchain.
- **No bulk filter endpoint**: the API only exposes `GET /filter/{id}` for a single filter at a time. Solved by collecting the distinct filter IDs referenced across all restaurants and fetching them concurrently with `async`/`coroutineScope`, instead of sequential calls.
- **Design fidelity vs. theming**: some UI elements (like the restaurant card background) needed to stay a fixed white regardless of light/dark mode, per the Figma spec. Instead of hardcoding `Color.White` inline, this was captured as a named, documented token (`MunchiesCardBackground`) so the exception is explicit and easy to revisit.

## Done By

Patricia Gea Rodrigues
https://patriciageadev.vercel.app/

patricia.gea@gmail.com


