import Foundation

public struct Sponsor: Sendable, Equatable {
    public let name: String
    public let logo: URL
    public let link: URL
    public let plan: SponsorPlan

    public init(
        name: String,
        logo: URL,
        link: URL,
        plan: SponsorPlan
    ) {
        self.name = name
        self.logo = logo
        self.link = link
        self.plan = plan
    }
}

public enum SponsorPlan: String, Sendable, Equatable {
    case platinum = "PLATINUM"
    case gold = "GOLD"
    case silver = "SILVER"
    case bronze = "BRONZE"
    case supporter = "SUPPORTER"
}
