import Foundation

public struct EventMap: Sendable {
    public let events: [EventMapEvent]
    
    public init(events: [EventMapEvent]) {
        self.events = events
    }
}
