import SwiftUI
import Theme

public struct SponsorScreen: View {
    @State private var presenter = SponsorPresenter()

    public init() {}

    public var body: some View {
        Group {
            if presenter.isLoading {
                ProgressView()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            } else {
                ScrollView {
                    LazyVStack(spacing: 32) {
                        ForEach(presenter.sponsors) { category in
                            SponsorSection(category: category) { sponsor in
                                presenter.sponsorTapped(sponsor)
                            }
                        }
                    }
                    .padding(.top, 24)
                    .padding(.bottom, 80)
                }
            }
        }
        // TODO: Use AssetColors.Background when available
        .background(AssetColors.background.swiftUIColor)  // #111418
        .navigationTitle("スポンサー")
        #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
        #endif
        .task {
            await presenter.loadSponsors()
        }
    }
}

#Preview {
    SponsorScreen()
}
