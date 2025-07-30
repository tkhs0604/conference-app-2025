package io.github.droidkaigi.confsched.testing.annotations

import kotlin.reflect.KClass

actual abstract class Runner

actual class UiTestRunner : Runner()

actual annotation class RunWith actual constructor(actual val value: KClass<out Runner>)

actual typealias ComposeTest = kotlin.test.Test
