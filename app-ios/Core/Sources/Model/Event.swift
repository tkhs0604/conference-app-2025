import Foundation

public struct Event: Identifiable, Hashable {
    public let id: String
    public let title: String
    public let description: String
    public let room: Room
    public let moreDetailUrl: URL?
    
    public init(id: String, title: String, description: String, room: Room, moreDetailUrl: URL?) {
        self.id = id
        self.title = title
        self.description = description
        self.room = room
        self.moreDetailUrl = moreDetailUrl
    }
}
