package io.github.droidkaigi.confsched.model.core

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant

enum class DroidKaigi2025Day(
    private val visibleForUsers: Boolean,
    private val date: LocalDate,
) {
    // We are not using Workday sessions in the app
    Workday(
        visibleForUsers = false,
        date = LocalDate(2025, 9, 10),
    ),
    ConferenceDay1(
        visibleForUsers = true,
        date = LocalDate(2025, 9, 11),
    ),
    ConferenceDay2(
        visibleForUsers = true,
        date = LocalDate(2025, 9, 12),
    ),
    ;

    val dayOfMonth: Int = date.dayOfMonth
    val start: Instant = date.atStartOfDayIn(tz)
    val end: Instant = date.atTime(23, 59, 59, 999_999_999).toInstant(tz)

    fun tabIndex(): Int {
        return entries
            .sortedBy { it.ordinal }
            .filter { it.visibleForUsers }
            .indexOf(this)
    }

    fun monthAndDay(): String {
        return "${date.monthNumber}/${date.dayOfMonth}"
    }

    companion object Companion {
        fun ofOrNull(time: Instant): DroidKaigi2025Day? {
            return entries.firstOrNull {
                time in it.start..it.end
            }
        }

        fun visibleDays(): List<DroidKaigi2025Day> {
            return entries.filter { it.visibleForUsers }
        }

        /**
         * @return appropriate initial day for now
         */
        fun initialSelectedTabDay(clock: Clock): DroidKaigi2025Day {
            val reversedEntries = visibleDays().sortedByDescending { it.ordinal }
            var selectedDay = reversedEntries.last()
            for (entry in reversedEntries) {
                if (clock.now() <= entry.end) selectedDay = entry
            }
            return selectedDay
        }
    }
}

private val tz = TimeZone.of("UTC+9")
