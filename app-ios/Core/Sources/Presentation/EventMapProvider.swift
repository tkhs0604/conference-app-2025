import Dependencies
import Model
import SwiftUI
import UseCase

@Observable
public final class EventMapProvider {
    @ObservationIgnored
    @Dependency(\.eventMapUseCase) private var eventMapUseCase

    public var eventMap: EventMap?

    // UI State
    public var events: [(Int, EventMapEvent)] = []

    public init() {}

    @MainActor
    public func fetchEvents() async {
        do {
            eventMap = try await eventMapUseCase.load()
            events = Array((eventMap?.events ?? []).enumerated())
        } catch {
            print(error)
        }
    }
}
