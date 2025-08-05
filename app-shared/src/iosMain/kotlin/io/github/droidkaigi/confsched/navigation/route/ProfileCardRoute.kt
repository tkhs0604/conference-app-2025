package io.github.droidkaigi.confsched.navigation.route

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object ProfileCardTabRoute : MainTabRoute {
    override val rootRouteClass: KClass<*> get() = ProfileCardTabRoute::class
}
