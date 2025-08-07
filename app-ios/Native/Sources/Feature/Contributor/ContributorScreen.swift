import SwiftUI
import Theme

public struct ContributorScreen: View {
    @State private var presenter = ContributorPresenter()

    public init() {}

    public var body: some View {
        Group {
            if presenter.isLoading {
                ProgressView()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            } else {
                ScrollView {
                    LazyVStack(spacing: 0) {
                        ForEach(presenter.contributors) { contributor in
                            VStack(spacing: 0) {
                                ContributorListItem(contributor: contributor)
                                    .padding(.horizontal, 16)
                                    .contentShape(Rectangle())
                                    .onTapGesture {
                                        presenter.contributorTapped(contributor)
                                        // TODO: Open GitHub profile
                                    }

                                Divider()
                                    .padding(.leading, 80)
                            }
                        }
                    }
                    .padding(.bottom, 80)  // Tab bar padding
                }
            }
        }
        .background(Color.primary.opacity(0.05))
        .navigationTitle("Contributors")
        #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
        #endif
        .task {
            await presenter.loadContributors()
        }
    }
}

#Preview {
    ContributorScreen()
}
