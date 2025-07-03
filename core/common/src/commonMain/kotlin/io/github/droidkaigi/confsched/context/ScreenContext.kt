package io.github.droidkaigi.confsched.context

/**
 * Context for screen root composable functions.
 *
 * Inherit this interface then define required dependencies for the screen.
 *
 * Example:
 * ```kotlin
 * abstract class MyScreenScope private constructor()
 *
 * @ContributesGraphExtension(MyScreenScope::class)
 * interface MyScreenContext : ScreenContext {
 *     val queryKey: QueryKey<String>
 *
 *     @ContributesGraphExtension.Factory(AppScope::class)
 *     fun interface Factory {
 *         fun createMyScreenContext(): MyScreenContext
 *     }
 * }
 * ```
 *
 * Then you can use `MyScreenContext` in your screen root composable function:
 * ```kotlin
 * context(screenContext: MyScreenContext)
 * @Composable
 * fun MyScreenRoot() {
 *     val injectedQueryKey = rememberQuery(screenContext.queryKey)
 *
 *     SoilDataBoundary(queryKey) { fetchedStringData ->
 *         val eventFlow = rememberEventFlow<MyScreenEvent>()
 *         val uiState = screenContext.presenter(eventFlow, fetchedStringData)
 *
 *         MyScreen()
 *     }
 * }
 */
interface ScreenContext
