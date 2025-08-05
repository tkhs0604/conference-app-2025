package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.ptr
import kotlinx.datetime.toNSDate
import platform.EventKit.EKEntityType
import platform.EventKit.EKEvent
import platform.EventKit.EKEventStore
import platform.EventKit.EKSpan
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Composable
actual fun rememberExternalNavController(): ExternalNavController {
    return IosExternalNavController()
}

internal class IosExternalNavController : ExternalNavController {
    override fun navigate(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        } else {
            println("Failed to navigate to URL: $url")
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun navigateToCalendarRegistration(timetableItem: TimetableItem) {
        val eventStore = EKEventStore()

        eventStore.requestAccessToEntityType(EKEntityType.EKEntityTypeEvent) { granted, error ->
            if (granted) {
                val event = EKEvent.eventWithEventStore(eventStore)
                event.startDate = timetableItem.startsAt.toNSDate()
                event.endDate = timetableItem.endsAt.toNSDate()
                event.title = timetableItem.title.currentLangTitle
                event.notes = timetableItem.url
                event.location = timetableItem.room.name.currentLangTitle
                event.calendar = eventStore.defaultCalendarForNewEvents

                memScoped {
                    val errorPtr = alloc<ObjCObjectVar<NSError?>>()
                    val success = eventStore.saveEvent(event, span = EKSpan.EKSpanThisEvent, error = errorPtr.ptr)
                    if (success) {
                        println("Event added to calendar: ${event.title}")
                    } else {
                        println("Failed to add event to calendar: ${error?.localizedDescription ?: "Unknown error"}")
                    }
                }
            } else {
                println("Access to calendar not granted: $error")
            }
        }
    }

    override fun onShareClick(timetableItem: TimetableItem) {
        TODO("Not yet implemented")
    }
}
