import Dependencies
import Model
import Observation
import UseCase

@Observable
public final class EventMapProvider {
    @ObservationIgnored
    @Dependency(\.eventMapUseCase) private var eventMapUseCase

    // UI State
    public var events: [EventMapEvent] = []

    public init() {}

    @MainActor
    public func fetchEvents() async {
        do {
            let eventMap = try await eventMapUseCase.load()
            events = eventMap.events
        } catch {
            print(error)
        }
    }
}
