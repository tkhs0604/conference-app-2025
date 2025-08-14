import Foundation

public struct EventMapEvent: Hashable, Sendable, Equatable {
    public let name: MultiLangText
    public let description: MultiLangText
    public let room: Room
    public let moreDetailUrl: URL?
    public let message: MultiLangText?

    public init(
        name: MultiLangText, description: MultiLangText, room: Room, moreDetailUrl: URL?, message: MultiLangText?
    ) {
        self.name = name
        self.description = description
        self.room = room
        self.moreDetailUrl = moreDetailUrl
        self.message = message
    }

    public func hash(into hasher: inout Hasher) {
        hasher.combine(name.enTitle)
        hasher.combine(room.name.enTitle)
    }
}
