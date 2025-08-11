import Foundation

public struct EventMapEvent: Identifiable, Hashable, Sendable {
    public let id: String
    public let title: String
    public let description: String
    public let room: Room
    public let moreDetailUrl: URL?
    public let message: String?

    public init(id: String, title: String, description: String, room: Room, moreDetailUrl: URL?, message: String?) {
        self.id = id
        self.title = title
        self.description = description
        self.room = room
        self.moreDetailUrl = moreDetailUrl
        self.message = message
    }
}
