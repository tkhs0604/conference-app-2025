import Dependencies
import Foundation
import Model
import UseCase

@Observable
@MainActor
public final class SponsorProvider {
    @ObservationIgnored
    @Dependency(\.sponsorsUseCase) private var sponsorsUseCase

    public var sponsors: [SponsorCategory] = []

    public init() {}

    public func loadSponsorCategories() async {
        for await sponsorsList in sponsorsUseCase.load() {
            // Group sponsors by their plan
            let groupedSponsors = Dictionary(grouping: sponsorsList) { $0.plan }

            // Create sponsor categories based on plans
            var categories: [SponsorCategory] = []

            // Define the order of tiers
            let tierOrder: [(SponsorPlan, SponsorCategory.SponsorTier, String)] = [
                (.platinum, .platinum, "PLATINUM SPONSORS"),
                (.gold, .gold, "GOLD SPONSORS"),
                (.silver, .silver, "SILVER SPONSORS"),
                (.bronze, .bronze, "BRONZE SPONSORS"),
                (.supporter, .supporters, "SUPPORTERS"),
            ]

            for (plan, tier, name) in tierOrder {
                if let sponsors = groupedSponsors[plan], !sponsors.isEmpty {
                    let category = SponsorCategory(
                        id: plan.rawValue.lowercased(),
                        name: name,
                        sponsors: sponsors,
                        tier: tier
                    )
                    categories.append(category)
                }
            }

            sponsors = categories
        }
    }
}
