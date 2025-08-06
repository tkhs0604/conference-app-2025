import Dependencies
import Model
import UseCase
import SwiftUI

@Observable
public final class EventMapProvider {
    @ObservationIgnored
    @Dependency(\.eventMapUseCase) private var eventMapUseCase
    
    public var eventMap: EventMap?
    
    public init() {}
    
    @MainActor
    public func fetchEvents() async {
        do {
            eventMap = try await eventMapUseCase.load()
        } catch {
            print(error)
        }
    }
}
