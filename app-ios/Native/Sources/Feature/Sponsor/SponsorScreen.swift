import SwiftUI
import Theme

public struct SponsorScreen: View {
    @State private var presenter = SponsorPresenter()
    
    public init() {}
    
    public var body: some View {
        NavigationStack {
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
                                    // TODO: Open sponsor website
                                }
                            }
                        }
                        .padding(.vertical, 16)
                        .padding(.bottom, 80) // Tab bar padding
                    }
                }
            }
            .background(Color.primary.opacity(0.02))
            .navigationTitle("Sponsors")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
            #endif
            .task {
                await presenter.loadSponsors()
            }
        }
    }
}

#Preview {
    SponsorScreen()
}