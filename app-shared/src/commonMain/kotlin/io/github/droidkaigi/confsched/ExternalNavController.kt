package io.github.droidkaigi.confsched

import io.github.droidkaigi.confsched.model.sessions.TimetableItem

interface ExternalNavController {
    fun navigate(url: String)
    fun navigateToCalendarRegistration(timetableItem: TimetableItem)
    fun onShareClick(timetableItem: TimetableItem)
}
