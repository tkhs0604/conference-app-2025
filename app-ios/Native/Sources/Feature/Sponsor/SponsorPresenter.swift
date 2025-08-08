import Foundation
import Model
import Observation
import Presentation

@MainActor
@Observable
final class SponsorPresenter {
    var sponsors: [SponsorCategory] = []
    var isLoading = false

    init() {}

    func loadSponsors() async {
        isLoading = true
        // TODO: Load actual sponsor data from API
        sponsors = SponsorCategory.mockCategories
        isLoading = false
    }

    func sponsorTapped(_ sponsor: Sponsor) {
        // print("Sponsor tapped: \(sponsor.name)")
        // TODO: Open sponsor website
    }
}

// Mock models - TODO: Replace with actual models from shared module
struct SponsorCategory: Identifiable {
    let id: String
    let name: String
    let sponsors: [Sponsor]

    static let mockCategories: [SponsorCategory] = [
        SponsorCategory(
            id: "platinum",
            name: "PLATINUM",
            sponsors: [
                Sponsor(id: "1", name: "Google", logoUrl: nil, websiteUrl: URL(string: "https://google.com")),
                Sponsor(id: "2", name: "CyberAgent", logoUrl: nil, websiteUrl: URL(string: "https://cyberagent.co.jp"))
            ]
        ),
        SponsorCategory(
            id: "gold",
            name: "GOLD",
            sponsors: [
                Sponsor(id: "3", name: "DeNA", logoUrl: nil, websiteUrl: URL(string: "https://dena.com")),
                Sponsor(id: "4", name: "Mercari", logoUrl: nil, websiteUrl: URL(string: "https://mercari.com")),
                Sponsor(id: "5", name: "LINE", logoUrl: nil, websiteUrl: URL(string: "https://line.me"))
            ]
        ),
        SponsorCategory(
            id: "silver",
            name: "SILVER",
            sponsors: [
                Sponsor(id: "6", name: "Cookpad", logoUrl: nil, websiteUrl: URL(string: "https://cookpad.com")),
                Sponsor(id: "7", name: "Wantedly", logoUrl: nil, websiteUrl: URL(string: "https://wantedly.com")),
                Sponsor(id: "8", name: "Gunosy", logoUrl: nil, websiteUrl: URL(string: "https://gunosy.com")),
                Sponsor(id: "9", name: "SmartNews", logoUrl: nil, websiteUrl: URL(string: "https://smartnews.com"))
            ]
        )
    ]
}

struct Sponsor: Identifiable {
    let id: String
    let name: String
    let logoUrl: String?
    let websiteUrl: URL?
}
