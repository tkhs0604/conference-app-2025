import Foundation

public struct Contributor: Sendable, Equatable {
    public let id: String
    public let name: String
    public let url: String
    public let iconUrl: String
    
    public init(
        id: String,
        name: String,
        url: String,
        iconUrl: String
    ) {
        self.id = id
        self.name = name
        self.url = url
        self.iconUrl = iconUrl
    }
}