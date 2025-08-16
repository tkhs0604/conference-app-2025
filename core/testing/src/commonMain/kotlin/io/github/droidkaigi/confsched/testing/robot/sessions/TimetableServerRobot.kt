package io.github.droidkaigi.confsched.testing.robot.sessions

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.sessions.FakeSessionsApiClient

interface TimetableServerRobot {
    enum class ServerStatus {
        Operational,
        OperationalBothAssetAvailable,
        OperationalOnlySlideAssetAvailable,
        OperationalOnlyVideoAssetAvailable,
        OperationalMessageExists,
        Error,
    }

    fun setupTimetableServer(serverStatus: ServerStatus)
}

@Inject
class DefaultTimetableServerRobot(
    private val fakeSessionsApiClient: FakeSessionsApiClient,
) : TimetableServerRobot {
    override fun setupTimetableServer(serverStatus: TimetableServerRobot.ServerStatus) {
        fakeSessionsApiClient.setup(
            when (serverStatus) {
                TimetableServerRobot.ServerStatus.Operational -> FakeSessionsApiClient.Status.Operational
                TimetableServerRobot.ServerStatus.OperationalBothAssetAvailable -> FakeSessionsApiClient.Status.OperationalBothAssetAvailable
                TimetableServerRobot.ServerStatus.OperationalOnlySlideAssetAvailable -> FakeSessionsApiClient.Status.OperationalOnlySlideAssetAvailable
                TimetableServerRobot.ServerStatus.OperationalOnlyVideoAssetAvailable -> FakeSessionsApiClient.Status.OperationalOnlyVideoAssetAvailable
                TimetableServerRobot.ServerStatus.OperationalMessageExists -> FakeSessionsApiClient.Status.OperationalMessageExists
                TimetableServerRobot.ServerStatus.Error -> FakeSessionsApiClient.Status.Error
            },
        )
    }
}
