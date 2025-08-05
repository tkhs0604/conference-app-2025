package io.github.droidkaigi.confsched.navigation.route

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object TimetableTabRoute : MainTabRoute {
    override val rootRouteClass: KClass<*> get() = TimetableRoute::class
}

@Serializable
data object TimetableRoute

@Serializable
data class TimetableItemDetailRoute(val id: String)

@Serializable
data object SearchRoute
