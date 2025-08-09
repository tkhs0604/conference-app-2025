import Foundation

public struct Sponsor: Sendable, Equatable {
    public let name: String
    public let logo: String
    public let link: String
    public let plan: SponsorPlan
    
    public init(
        name: String,
        logo: String,
        link: String,
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