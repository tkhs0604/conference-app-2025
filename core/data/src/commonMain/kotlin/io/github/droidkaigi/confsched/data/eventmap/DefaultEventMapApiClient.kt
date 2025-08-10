package io.github.droidkaigi.confsched.data.eventmap

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.core.NetworkExceptionHandler
import io.github.droidkaigi.confsched.data.eventmap.response.EventMapResponse
import io.github.droidkaigi.confsched.data.eventmap.response.MessageResponse
import io.github.droidkaigi.confsched.model.core.MultiLangText
import io.github.droidkaigi.confsched.model.core.RoomIcon
import io.github.droidkaigi.confsched.model.eventmap.EventMapEvent
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

internal interface EventMapApi {
    @GET("/events/droidkaigi2025/projects")
    suspend fun getEventMap(): EventMapResponse
}

@ContributesBinding(DataScope::class)
@Inject
public class DefaultEventMapApiClient(
    private val networkExceptionHandler: NetworkExceptionHandler,
    ktorfit: Ktorfit,
) : EventMapApiClient {
    private val eventMapApi = ktorfit.createEventMapApi()

    public override suspend fun eventMapEvents(): PersistentList<EventMapEvent> {
        return networkExceptionHandler {
            eventMapApi.getEventMap()
        }.toEventMapList()
    }
}

public fun EventMapResponse.toEventMapList(): PersistentList<EventMapEvent> {
    val roomIdToNameMap = this.rooms.associateBy({ it.id }, { it.name.ja to it.name.en })

    return this.projects
        .mapNotNull { project ->
            roomIdToNameMap[project.roomId]?.let { roomName ->
                EventMapEvent(
                    name = MultiLangText(
                        jaTitle = project.title.ja,
                        enTitle = project.title.en,
                    ),
                    roomName = MultiLangText(
                        jaTitle = roomName.first,
                        enTitle = roomName.second,
                    ),
                    roomIcon = roomName.second.toRoomIcon(),
                    description = MultiLangText(
                        jaTitle = project.i18nDesc.ja,
                        enTitle = project.i18nDesc.en,
                    ),
                    moreDetailsUrl = project.moreDetailsUrl,
                    message = project.message?.toMultiLangText(),
                )
            }
        }
        .toPersistentList()
}

private fun String.toRoomIcon(): RoomIcon = when (this) {
    "Iguana" -> RoomIcon.Square
    "Hedgehog" -> RoomIcon.Diamond
    "Giraffe" -> RoomIcon.Circle
    "Flamingo" -> RoomIcon.Rhombus
    "Jellyfish" -> RoomIcon.Triangle
    else -> RoomIcon.None
}

private fun MessageResponse.toMultiLangText() =
    if (ja != null && en != null) MultiLangText(jaTitle = ja, enTitle = en) else null
