import Component
import Model
import SwiftUI
import Theme

public struct FavoriteScreen: View {
    @State private var presenter = FavoritePresenter()
    let onNavigate: (FavoriteNavigationDestination) -> Void

    public init(onNavigate: @escaping (FavoriteNavigationDestination) -> Void = { _ in }) {
        self.onNavigate = onNavigate
    }

    public var body: some View {
        Group {
            if presenter.favoriteTimetableItems.isEmpty {
                emptyView
            } else {
                ScrollView {
                    LazyVStack(spacing: 0) {
                        ForEach(presenter.favoriteTimetableItems.indices, id: \.self) { index in
                            let timeGroup = presenter.favoriteTimetableItems[index]

                            TimeGroupList(
                                timeGroup: timeGroup,
                                onItemTap: { item in
                                    onNavigate(.timetableDetail(item))
                                },
                                onFavoriteTap: { item, _ in
                                    presenter.toggleFavorite(item)
                                }
                            )

                            if index < presenter.favoriteTimetableItems.count - 1 {
                                DashedDivider()
                                    .padding(.vertical, 16)
                            }
                        }
                    }
                    .padding(.horizontal, 16)
                    .padding(.vertical, 20)
                    .padding(.bottom, 80)  // Tab bar padding
                }
            }
        }
        .background(AssetColors.surface.swiftUIColor)
        .navigationTitle("Favorites")
        #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
        #endif
        .onAppear {
            presenter.loadInitial()
        }
    }

    @ViewBuilder
    private var emptyView: some View {
        VStack(spacing: 24) {
            // TODO: Replace with actual empty state illustration
            Image(systemName: "heart.slash")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 120, height: 120)
                .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor.opacity(0.5))
                .accessibilityLabel("No favorites")

            VStack(spacing: 8) {
                Text("No Favorites Yet")
                    .font(.title2)
                    .fontWeight(.semibold)

                Text("Tap the heart icon on sessions to add them to your favorites")
                    .font(.body)
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 40)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

#Preview {
    FavoriteScreen()
}
