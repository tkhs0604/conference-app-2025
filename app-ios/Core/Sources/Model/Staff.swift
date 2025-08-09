import Foundation

public struct Staff: Sendable, Equatable {
    public let id: String
    public let name: String
    public let iconUrl: URL
    public let profileUrl: URL?
    
    public init(
        id: String,
        name: String,
        iconUrl: URL,
        profileUrl: URL? = nil
    ) {
        self.id = id
        self.name = name
        self.iconUrl = iconUrl
        self.profileUrl = profileUrl
    }
}