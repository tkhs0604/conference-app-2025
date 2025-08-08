import Foundation
import Model
import UseCase

struct SponsorUseCaseImpl {
    func load() async -> [Model.SponsorCategory] {
        // TODO: Replace with actual KMP implementation when sponsorsRepository is available
        // For now, return mock data
        Model.SponsorCategory.mockCategories
    }
}

extension Model.SponsorCategory {
    static let mockCategories: [Model.SponsorCategory] = [
        Model.SponsorCategory(
            id: "platinum",
            name: "PLATINUM SPONSORS",
            sponsors: [
                Model.Sponsor(id: "1", name: "Google", logoUrl: nil, websiteUrl: URL(string: "https://google.com")),
                Model.Sponsor(
                    id: "2", name: "CyberAgent", logoUrl: nil, websiteUrl: URL(string: "https://cyberagent.co.jp")),
                Model.Sponsor(id: "3", name: "Recruit", logoUrl: nil, websiteUrl: URL(string: "https://recruit.co.jp")),
            ],
            tier: .platinum
        ),
        Model.SponsorCategory(
            id: "gold",
            name: "GOLD SPONSORS",
            sponsors: [
                Model.Sponsor(id: "4", name: "DeNA", logoUrl: nil, websiteUrl: URL(string: "https://dena.com")),
                Model.Sponsor(id: "5", name: "Mercari", logoUrl: nil, websiteUrl: URL(string: "https://mercari.com")),
                Model.Sponsor(id: "6", name: "LINE", logoUrl: nil, websiteUrl: URL(string: "https://line.me")),
                Model.Sponsor(id: "7", name: "Yahoo", logoUrl: nil, websiteUrl: URL(string: "https://yahoo.co.jp")),
                Model.Sponsor(id: "8", name: "Rakuten", logoUrl: nil, websiteUrl: URL(string: "https://rakuten.co.jp")),
                Model.Sponsor(id: "9", name: "M3", logoUrl: nil, websiteUrl: URL(string: "https://m3.com")),
                Model.Sponsor(id: "10", name: "Mixi", logoUrl: nil, websiteUrl: URL(string: "https://mixi.co.jp")),
                Model.Sponsor(id: "11", name: "GREE", logoUrl: nil, websiteUrl: URL(string: "https://gree.co.jp")),
            ],
            tier: .gold
        ),
        Model.SponsorCategory(
            id: "supporters",
            name: "SUPPORTERS",
            sponsors: [
                Model.Sponsor(id: "12", name: "Cookpad", logoUrl: nil, websiteUrl: URL(string: "https://cookpad.com")),
                Model.Sponsor(
                    id: "13", name: "Wantedly", logoUrl: nil, websiteUrl: URL(string: "https://wantedly.com")),
                Model.Sponsor(id: "14", name: "Gunosy", logoUrl: nil, websiteUrl: URL(string: "https://gunosy.com")),
                Model.Sponsor(
                    id: "15", name: "SmartNews", logoUrl: nil, websiteUrl: URL(string: "https://smartnews.com")),
                Model.Sponsor(id: "16", name: "Zaim", logoUrl: nil, websiteUrl: URL(string: "https://zaim.co.jp")),
                Model.Sponsor(id: "17", name: "Retty", logoUrl: nil, websiteUrl: URL(string: "https://retty.co.jp")),
                Model.Sponsor(id: "18", name: "Sansan", logoUrl: nil, websiteUrl: URL(string: "https://sansan.com")),
                Model.Sponsor(id: "19", name: "Freee", logoUrl: nil, websiteUrl: URL(string: "https://freee.co.jp")),
                Model.Sponsor(
                    id: "20", name: "Money Forward", logoUrl: nil, websiteUrl: URL(string: "https://moneyforward.com")),
                Model.Sponsor(id: "21", name: "Timee", logoUrl: nil, websiteUrl: URL(string: "https://timee.co.jp")),
                Model.Sponsor(id: "22", name: "BASE", logoUrl: nil, websiteUrl: URL(string: "https://thebase.com")),
                Model.Sponsor(id: "23", name: "PayPay", logoUrl: nil, websiteUrl: URL(string: "https://paypay.ne.jp")),
            ],
            tier: .supporters
        ),
    ]
}
