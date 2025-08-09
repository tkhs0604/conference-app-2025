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
                Model.Sponsor(
                    id: "1",
                    name: "Google",
                    logoUrl: URL(string: "https://placehold.co/400x200/EEE/31343C?text=Google")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://google.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "2",
                    name: "CyberAgent",
                    logoUrl: URL(string: "https://placehold.co/400x200/EEE/31343C?text=CyberAgent")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://cyberagent.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "3",
                    name: "Recruit",
                    logoUrl: URL(string: "https://placehold.co/400x200/EEE/31343C?text=Recruit")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://recruit.co.jp") ?? URL(fileURLWithPath: "/")
                ),
            ],
            tier: .platinum
        ),
        Model.SponsorCategory(
            id: "gold",
            name: "GOLD SPONSORS",
            sponsors: [
                Model.Sponsor(
                    id: "4",
                    name: "DeNA",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=DeNA")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://dena.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "5",
                    name: "Mercari",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=Mercari")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://mercari.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "6",
                    name: "LINE",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=LINE")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://line.me") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "7",
                    name: "Yahoo",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=Yahoo")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://yahoo.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "8",
                    name: "Rakuten",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=Rakuten")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://rakuten.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "9",
                    name: "M3",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=M3")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://m3.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "10",
                    name: "Mixi",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=Mixi")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://mixi.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "11",
                    name: "GREE",
                    logoUrl: URL(string: "https://placehold.co/300x150/EEE/31343C?text=GREE")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://gree.co.jp") ?? URL(fileURLWithPath: "/")
                ),
            ],
            tier: .gold
        ),
        Model.SponsorCategory(
            id: "supporters",
            name: "SUPPORTERS",
            sponsors: [
                Model.Sponsor(
                    id: "12",
                    name: "Cookpad",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Cookpad")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://cookpad.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "13",
                    name: "Wantedly",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Wantedly")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://wantedly.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "14",
                    name: "Gunosy",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Gunosy")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://gunosy.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "15",
                    name: "SmartNews",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=SmartNews")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://smartnews.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "16",
                    name: "Zaim",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Zaim")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://zaim.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "17",
                    name: "Retty",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Retty")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://retty.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "18",
                    name: "Sansan",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Sansan")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://sansan.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "19",
                    name: "Freee",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Freee")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://freee.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "20",
                    name: "Money Forward",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=MoneyForward")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://moneyforward.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "21",
                    name: "Timee",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=Timee")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://timee.co.jp") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "22",
                    name: "BASE",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=BASE")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://thebase.com") ?? URL(fileURLWithPath: "/")
                ),
                Model.Sponsor(
                    id: "23",
                    name: "PayPay",
                    logoUrl: URL(string: "https://placehold.co/200x100/EEE/31343C?text=PayPay")
                        ?? URL(fileURLWithPath: "/"),
                    websiteUrl: URL(string: "https://paypay.ne.jp") ?? URL(fileURLWithPath: "/")
                ),
            ],
            tier: .supporters
        ),
    ]
}
