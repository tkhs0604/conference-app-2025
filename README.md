![image](assets/readme_header.png)

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

## Requirements

Stable Android Studio Narwhal or higher. You can download it from
[this page](https://developer.android.com/studio).

## Design

You can check out the UI design on Figma:
[DroidKaigi 2025 App UI](https://www.figma.com/design/1YjyMBNVLEGcHP4W7UNzDE/DroidKaigi-2025-App-UI)

## Development

### Overview of the architecture

Based on last year's architecture, we've redesigned our approach to improve the app development
experience further by introducing the following concepts:

- A more composable-first approach, replacing repository classes
  with [Soil](https://github.com/soil-kt/soil)
- Compile-time-safe dependency injection with [Metro](https://github.com/ZacSweers/metro)
- Structuring the codebase with context parameters to provide clearer semantic meaning to Composable
  functions.

Here's a big picture of this year's architecture:

![](assets/architecture.png)

The following sections describe how this architecture works in practice.

### Dependency Injection via ScreenContext

Unlike last year, we no longer rely on `CompositionLocal` to provide dependencies.  
Instead, we use [Metro](https://github.com/ZacSweers/metro) to resolve each screen's dependencies
at compile time and provide them via a single `ScreenContext`.

This approach clarifies which dependencies are required for a screen and helps prevent potential
runtime errors caused by missing dependencies.

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

More importantly, we provide the ScreenContext as a context parameter to give Composable functions
additional semantic context. This allows us to restrict the usage of certain functions to their
specific screen scope—for example, the SoilDataBoundary described in the following section.

### Data Boundary with Soil

At the root of the screen, we can use `SoilDataBoundary` to separate data fetching logic from
UI logic. Within its trailing content lambda, we can safely assume that all data is
available and ready to be used in the UI.

```kotlin
context(screenContext: TimetableScreenContext)
@Composable
fun TimetableScreenRoot(...) {
    SoilDataBoundary(
        state1 = rememberQuery(screenContext.timetableQueryKey),
        state2 = rememberSubscription(screenContext.favoriteTimetableIdsSubscriptionKey),
        fallback = ...,
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

The `QueryKey` and `SubscriptionKey` provided by Soil are responsible for fetching
the server or database state, and runtime caching is handled by Soil’s `SwrClient`.
Therefore, we no longer need repository implementations to manually manage data fetching and caching.

```kotlin
typealias TimetableQueryKey = QueryKey<Timetable>

@ContributesBinding(DataScope::class)
@Inject
public class DefaultTimetableQueryKey(
    private val sessionsApiClient: SessionsApiClient,
    private val dataStore: SessionCacheDataStore,
) : TimetableQueryKey by buildQueryKey(
    id = QueryId("timetable"),
    fetch = {
        val response = sessionsApiClient.sessionsAllResponse()
        dataStore.save(response)
        response.toTimetable()
    },
) {
    override fun onPreloadData(): QueryPreloadData<Timetable>? {
        return { dataStore.getCache()?.toTimetable() }
    }
}
```

### Composing the Presenters

The next step is to present the data in the UI. Presenters are responsible for handling UI
events and constructing the UI state. As with last year, we designed them as composable functions.

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
