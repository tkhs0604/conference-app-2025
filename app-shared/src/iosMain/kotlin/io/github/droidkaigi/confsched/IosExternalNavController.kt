package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.model.sessions.TimetableItem

@Composable
actual fun rememberExternalNavController(): ExternalNavController {
    return IosExternalNavController()
}

internal class IosExternalNavController : ExternalNavController {
    override fun navigate(url: String) {
        TODO("Not yet implemented")
    }

    override fun navigateToCalendarRegistration(timetableItem: TimetableItem) {
        TODO("Not yet implemented")
    }

    override fun onShareClick(timetableItem: TimetableItem) {
        TODO("Not yet implemented")
    }
}
