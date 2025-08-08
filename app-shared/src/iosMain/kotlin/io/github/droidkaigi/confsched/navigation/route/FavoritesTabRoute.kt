package io.github.droidkaigi.confsched.navigation.route

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
object FavoritesTabRoute : MainTabRoute {
    override val rootRouteClass: KClass<*> get() = Favorites::class
}

@Serializable
data object Favorites
