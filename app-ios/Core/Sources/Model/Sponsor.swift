import Foundation

public struct SponsorCategory: Identifiable, Sendable {
    public let id: String
    public let name: String
    public let sponsors: [Sponsor]
    public let tier: SponsorTier

    public enum SponsorTier: Sendable {
        case platinum
        case gold
        case silver
        case bronze
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
    public let logoUrl: URL
    public let websiteUrl: URL
    public let plan: SponsorPlan

    public init(
        id: String,
        name: String,
        logoUrl: URL,
        websiteUrl: URL,
        plan: SponsorPlan
    ) {
        self.id = id
        self.name = name
        self.logoUrl = logoUrl
        self.websiteUrl = websiteUrl
        self.plan = plan
    }
}

public enum SponsorPlan: String, Sendable {
    case platinum = "PLATINUM"
    case gold = "GOLD"
    case silver = "SILVER"
    case bronze = "BRONZE"
    case supporter = "SUPPORTER"
}