package io.github.droidkaigi.confsched.testing.annotations

import kotlin.reflect.KClass

expect abstract class Runner

expect class UiTestRunner : Runner

expect annotation class RunWith(val value: KClass<out Runner>)

expect annotation class ComposeTest()
