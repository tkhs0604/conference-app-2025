package io.github.droidkaigi.confsched.navigation.route

import kotlin.reflect.KClass

sealed interface MainTabRoute {
    val rootRouteClass: KClass<*>
}
