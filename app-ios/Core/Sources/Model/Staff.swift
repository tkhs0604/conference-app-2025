import Foundation

public struct Staff: Sendable, Equatable {
    public let id: String
    public let name: String
    public let iconUrl: String
    public let profileUrl: String?
    
    public init(
        id: String,
        name: String,
        iconUrl: String,
        profileUrl: String? = nil
    ) {
        self.id = id
        self.name = name
        self.iconUrl = iconUrl
        self.profileUrl = profileUrl
    }
}