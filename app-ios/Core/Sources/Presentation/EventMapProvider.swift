import Dependencies
import Model
import Observation
import UseCase

@Observable
public final class EventMapProvider {
    @ObservationIgnored
    @Dependency(\.eventMapUseCase) private var eventMapUseCase
    
    @ObservationIgnored
    private var fetchEvents: Task<Void, Never>?

    // UI State
    public var events: [EventMapEvent] = []

    public init() {}

    @MainActor
    public func subscribeEventMapEventsIfNeeded() {
        guard fetchEvents == nil else { return }
        
        self.fetchEvents = Task {
            for await events in eventMapUseCase.load() {
                self.events = events
            }
        }
    }
}
