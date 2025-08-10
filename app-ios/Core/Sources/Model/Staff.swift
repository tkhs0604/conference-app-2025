import Foundation

public struct Staff: Sendable, Equatable, Identifiable {
    public let id: String
    public let name: String
    public let iconUrl: URL
    public let profileUrl: URL?
    public let role: String?

    public init(
        id: String,
        name: String,
        iconUrl: URL,
        profileUrl: URL? = nil,
        role: String? = nil
    ) {
        self.id = id
        self.name = name
        self.iconUrl = iconUrl
        self.profileUrl = profileUrl
        self.role = role
    }
}
