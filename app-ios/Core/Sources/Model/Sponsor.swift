import Foundation

public struct SponsorCategory: Identifiable, Sendable {
    public let id: String
    public let name: String
    public let sponsors: [Sponsor]
    public let tier: SponsorTier

    public enum SponsorTier: Sendable {
        case platinum
        case gold
        case supporters
    }

    public init(
        id: String,
        name: String,
        sponsors: [Sponsor],
        tier: SponsorTier
    ) {
        self.id = id
        self.name = name
        self.sponsors = sponsors
        self.tier = tier
    }
}

public struct Sponsor: Identifiable, Sendable {
    public let id: String
    public let name: String
    public let logoUrl: String?
    public let websiteUrl: URL?

    public init(
        id: String,
        name: String,
        logoUrl: String?,
        websiteUrl: URL?
    ) {
        self.id = id
        self.name = name
        self.logoUrl = logoUrl
        self.websiteUrl = websiteUrl
    }
}
