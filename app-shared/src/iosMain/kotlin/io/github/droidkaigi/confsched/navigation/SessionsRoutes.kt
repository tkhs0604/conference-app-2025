package io.github.droidkaigi.confsched.navigation

import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.serialization.Serializable

@Serializable
data object TimetableTabRoute

@Serializable
data object TimetableRoute

@Serializable
data class TimetableItemDetailRoute(val id: TimetableItemId)

@Serializable
data object SearchRoute
