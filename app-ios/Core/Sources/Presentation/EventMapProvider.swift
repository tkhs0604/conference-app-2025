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
            events = try await eventMapUseCase.load()
        } catch {
            print(error)
        }
    }
}
