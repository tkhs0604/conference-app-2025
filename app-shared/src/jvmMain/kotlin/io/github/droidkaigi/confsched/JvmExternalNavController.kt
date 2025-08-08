package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.droidkaigi.confsched.model.sessions.TimetableItem

@Composable
actual fun rememberExternalNavController(): ExternalNavController {
    return remember { JvmExternalNavController() }
}

class JvmExternalNavController : ExternalNavController {
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
