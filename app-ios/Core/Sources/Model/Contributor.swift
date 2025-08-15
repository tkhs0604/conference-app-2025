import Foundation

public struct Contributor: Sendable, Equatable {
    public let id: String
    public let name: String
    public let url: URL
    public let iconUrl: URL

    public init(
        id: String,
        name: String,
        url: URL,
        iconUrl: URL
    ) {
        self.id = id
        self.name = name
        self.url = url
        self.iconUrl = iconUrl
    }
}
