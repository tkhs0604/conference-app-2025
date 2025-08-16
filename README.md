# DroidKaigi 2025 official app

[DroidKaigi](https://2025.droidkaigi.jp) marks its 11th edition this year!
This conference is designed for Android developers to enhance knowledge sharing and communication.
It will take place over 3 days, from 10 to 12 September 2025.

## Features

The DroidKaigi 2025 official app offers a variety of features to enhance your conference experience:

- **Timetable**: View the schedule and bookmark your favorite sessions.
- **Profile cards**: Create and share your profile with other attendees.
- **Contributors**: Discover the contributors behind the app. ...and more!

## Contributing

We welcome contributions!

For a step-by-step guide on how to contribute, see
[CONTRIBUTING.md](CONTRIBUTING.md). It walks you through everything from
setting up your environment to submitting a pull request.

For Japanese speakers, a Japanese version is available:
[CONTRIBUTING.ja.md](CONTRIBUTING.ja.md).

コントリビューションの詳細な手順については [CONTRIBUTING.ja.md](CONTRIBUTING.ja.md) をご覧ください。
初めての方でもわかりやすいステップバイステップのガイドを用意しています。

## Development

### Overview of the architecture

Based on last year's architecture, we've redesigned our approach to improve the app development
experience further by introducing the following concepts:

- A more composable-first approach, replacing repository classes
  with [Soil](https://github.com/soil-kt/soil)
- Compile-time-safe dependency injection with [Metro](https://github.com/ZacSweers/metro)
- Structuring the codebase with context parameters to provide clearer semantic meaning to Composable
  functions.

![](assets/architecture.png)

The following sections describe how this architecture works in practice.

### Dependency Injection via ScreenContext

The entry point of each screen starts with a `ScreenContext`. It provides all the dependencies
required for the screen. Dependency injection is safely performed at compile-time by Metro.

```kotlin
context(screenContext: TimetableScreenContext)
@Composable
fun TimetableScreenRoot(...) {
    // ...
}

@ContributesGraphExtension(TimetableScope::class)
interface TimetableScreenContext : ScreenContext {
    val timetableSubscriptionKey: TimetableSubscriptionKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createTimetableScreenContext(): TimetableScreenContext
    }
}
```

### Data Boundary with Soil

In the root of the screen, we can use `SoilDataBoundary` to separate the data fetching logic from
the UI logic. Within its trailing content lambda, we can safely assume that all the data is
available and ready to be used in the UI.

```kotlin
context(screenContext: TimetableScreenContext)
@Composable
fun TimetableScreenRoot(...) {
    SoilDataBoundary(
        state1 = rememberSubscription(screenContext.timetableSubscriptionKey),
        state2 = rememberSubscription(screenContext.favoriteTimetableIdsSubscriptionKey),
        errorFallback = { ... }
    ) { timetable, favoriteTimetableItemIds ->
        // All data required to render the screen is guaranteed to be available here.
    }
}

context(_: ScreenContext) // This function can be called within any ScreenContext
@Composable
fun <T1, T2> SoilDataBoundary(
    state1: State<T1>,
    state2: State<T2>,
    ...,
    content: @Composable (T1, T2) -> Unit,
) {
    ErrorBoundary(...) {
        Suspense(...) {
            Await(state1, state2, content)
        }
    }
}
```

### Composing the Presenters

The next step is to present the data in the UI. Presenters are responsible for handling the UI
events and building the UI state. Like last year, we designed them as composable functions.

```kotlin
context(screenContext: TimetableScreenContext)
@Composable
fun TimetableScreenRoot(...) {
    SoilDataBoundary(...) { timetable, favoriteTimetableItemIds ->
        val eventFlow = rememberEventFlow<TimetableScreenEvent>()

        val uiState = timetableScreenPresenter(
            eventFlow = eventFlow,
            timetable = timetable.copy(bookmarks = favoriteTimetableItemIds),
        )

        TimetableScreen(uiState = uiState, ...)
    }
}

// The dependencies for presenter are also provided via screenContext.
context(screenContext: TimetableScreenContext)
@Composable
fun timetableScreenPresenter(
    eventFlow: EventFlow<TimetableScreenEvent>,
    timetable: Timetable,
): TimetableScreenUiState = providePresenterDefaults {
    // Build and return the UI state based on the given data and user events
}
```

Presenters receive `eventFlow`, then handle the UI events inside `EventEffect` and mutate the UI
state or database state.

```kotlin
context(screenContext: TimetableScreenContext)
@Composable
fun timetableScreenPresenter(
    eventFlow: EventFlow<TimetableScreenEvent>,
    timetable: Timetable,
): TimetableScreenUiState = providePresenterDefaults {
    val bookmarkMutation = rememberMutation(screenContext.favoriteTimetableItemIdMutationKey)

    EventEffect(eventFlow) { event ->
        when (event) {
            is TimetableScreenEvent.Bookmark -> {
                bookmarkMutation.mutate(event.timetableItemId)
            }
            // Handle other events...
        }
    }

    return TimetableScreenUiState(...)
}
```
