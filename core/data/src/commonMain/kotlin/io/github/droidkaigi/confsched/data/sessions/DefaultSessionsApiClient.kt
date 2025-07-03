package io.github.droidkaigi.confsched.data.sessions

import androidx.annotation.VisibleForTesting
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import io.github.droidkaigi.confsched.data.core.NetworkExceptionHandler
import io.github.droidkaigi.confsched.data.sessions.response.SessionsAllResponse
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day

internal interface SessionApi {
    @GET("events/droidkaigi2024/timetable")
    suspend fun getTimetable(): SessionsAllResponse
}

public class DefaultSessionsApiClient internal constructor(
    private val networkExceptionHandler: NetworkExceptionHandler,
    ktorfit: Ktorfit,
) : SessionsApiClient {
    private val sessionApi: SessionApi = ktorfit.createSessionApi()

    override suspend fun sessionsAllResponse(): SessionsAllResponse {
        return networkExceptionHandler {
            sessionApi.getTimetable()
        }
    }

    public companion object {
        @VisibleForTesting
        public fun SessionsAllResponse.filterConferenceDaySessions(): SessionsAllResponse {
            return copy(
                sessions = sessions.filter {
                    val startsAt = it.startsAt.toInstantAsJST()
                    DroidKaigi2025Day.visibleDays()
                        .any { day -> day.start <= startsAt && startsAt < day.end }
                },
            )
        }
    }
}
