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
                        // Total count header
                        VStack(spacing: 0) {
                            HStack {
                                VStack(alignment: .leading, spacing: 4) {
                                    Text("Total")
                                        .font(.custom(AssetFonts.Chango.regular, size: 15))
                                        .foregroundColor(AssetColors.onSurface.swiftUIColor)

                                    Text("\(presenter.contributors.count) persons")
                                        .font(.custom(AssetFonts.Chango.regular, size: 34))
                                        .foregroundColor(AssetColors.onSurface.swiftUIColor)
                                }
                                Spacer()
                            }
                            .padding(.horizontal, 16)
                            .padding(.vertical, 20)
                            Divider()
                        }
                        .background(AssetColors.surface.swiftUIColor)

                        ForEach(presenter.contributors) { contributor in
                            VStack(spacing: 0) {
                                ContributorListItem(contributor: contributor)
                                    .padding(.horizontal, 16)
                                    .contentShape(Rectangle())
                                    .onTapGesture {
                                        presenter.contributorTapped(contributor)
                                        // TODO: Open GitHub profile
                                    }
                                    .accessibilityAddTraits(.isButton)

                                Divider()
                                    .padding(.leading, 80)
                            }
                        }
                    }
                    .padding(.bottom, 80)  // Tab bar padding
                }
            }
        }
        .background(AssetColors.surface.swiftUIColor)
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
