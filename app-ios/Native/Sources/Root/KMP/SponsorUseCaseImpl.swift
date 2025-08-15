import Foundation
import Model
import UseCase
import shared

struct SponsorUseCaseImpl {
    func load() async -> [Model.SponsorCategory] {
        // Get sponsors from KMP repository
        let sponsorsRepository = KMPDependencyProvider.shared.appGraph.sponsorsRepository
        let sponsorsFlow = sponsorsRepository.sponsorsFlow()

        // Get the first value from the flow
        var allSponsors: [Model.Sponsor] = []
        for await sponsorsList in sponsorsFlow {
            allSponsors = sponsorsList.map { Model.Sponsor(from: $0) }
            break  // Take only the first emission for async load
        }

        // Group sponsors by their plan
        let groupedSponsors = Dictionary(grouping: allSponsors) { $0.plan }

        // Create sponsor categories based on plans
        var categories: [Model.SponsorCategory] = []

        // Define the order of tiers
        let tierOrder: [(Model.SponsorPlan, Model.SponsorCategory.SponsorTier, String)] = [
            (.platinum, .platinum, "PLATINUM SPONSORS"),
            (.gold, .gold, "GOLD SPONSORS"),
            (.silver, .silver, "SILVER SPONSORS"),
            (.bronze, .bronze, "BRONZE SPONSORS"),
            (.supporter, .supporters, "SUPPORTERS"),
        ]

        for (plan, tier, name) in tierOrder {
            if let sponsors = groupedSponsors[plan], !sponsors.isEmpty {
                let category = Model.SponsorCategory(
                    id: plan.rawValue.lowercased(),
                    name: name,
                    sponsors: sponsors,
                    tier: tier
                )
                categories.append(category)
            }
        }

        return categories
    }
}
