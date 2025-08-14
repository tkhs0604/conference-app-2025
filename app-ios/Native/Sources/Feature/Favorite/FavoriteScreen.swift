import Component
import Foundation
import Model
import SwiftUI
import Theme

public struct FavoriteScreen: View {
    @State private var presenter = FavoritePresenter()
    @State private var selectedDate: DateFilterView.DateFilter = .all
    let onNavigate: (FavoriteNavigationDestination) -> Void

    public init(onNavigate: @escaping (FavoriteNavigationDestination) -> Void = { _ in }) {
        self.onNavigate = onNavigate
    }

    public var body: some View {
        VStack(spacing: 0) {
            // Date filter chips
            DateFilterView(selectedDate: $selectedDate)
                .padding(.horizontal, 16)
                .padding(.vertical, 12)

            Group {
                if filteredItems.isEmpty {
                    emptyView
                } else {
                    ScrollView {
                        LazyVStack(spacing: 0) {
                            ForEach(filteredItems.indices, id: \.self) { index in
                                let timeGroup = filteredItems[index]

                                TimeGroupList(
                                    timeGroup: timeGroup,
                                    onItemTap: { item in
                                        onNavigate(.timetableDetail(item))
                                    },
                                    onFavoriteTap: { item, _ in
                                        presenter.toggleFavorite(item)
                                    }
                                )

                                if index < filteredItems.count - 1 {
                                    DashedDivider()
                                        .padding(.vertical, 16)
                                        .padding(.horizontal, 16)
                                }
                            }
                        }
                        .padding(.vertical, 20)
                        .padding(.bottom, 80)  // Tab bar padding
                    }
                }
            }
        }
        .background(AssetColors.background.swiftUIColor)
        .navigationTitle(String(localized: "お気に入り", bundle: .module))
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
            Image(systemName: "heart.fill")
                .resizable()
                .frame(width: 36, height: 36)
                .foregroundStyle(AssetColors.primaryFixed.swiftUIColor)
                .padding(24)
                .background(
                    RoundedRectangle(cornerRadius: 24)
                        .fill(AssetColors.onPrimary.swiftUIColor)
                )

            VStack(spacing: 8) {
                Text("登録されたセッションが\nありません", bundle: .module)
                    .font(Typography.titleLarge)
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                    .multilineTextAlignment(.center)

                Text("気になるセッションをお気に入り登録しましょう", bundle: .module)
                    .font(Typography.bodyMedium)
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 40)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    private var filteredItems: [TimetableTimeGroupItems] {
        switch selectedDate {
        case .all:
            return presenter.favoriteTimetableItems
        case .day1:
            return presenter.favoriteTimetableItems.filter { timeGroup in
                guard let firstItem = timeGroup.items.first else { return false }
                let calendar = Calendar.current
                let components = calendar.dateComponents([.day], from: firstItem.timetableItem.startsAt)
                return components.day == 12
            }
        case .day2:
            return presenter.favoriteTimetableItems.filter { timeGroup in
                guard let firstItem = timeGroup.items.first else { return false }
                let calendar = Calendar.current
                let components = calendar.dateComponents([.day], from: firstItem.timetableItem.startsAt)
                return components.day == 13
            }
        }
    }
}

#Preview {
    FavoriteScreen()
}
