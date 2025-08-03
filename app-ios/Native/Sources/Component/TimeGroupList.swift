import Model
import SwiftUI
import Theme

public struct TimeGroupList: View {
    let timeGroup: TimetableTimeGroupItems
    let onItemTap: (TimetableItemWithFavorite) -> Void
    let onFavoriteTap: (TimetableItemWithFavorite, CGPoint?) -> Void
    
    public init(
        timeGroup: TimetableTimeGroupItems,
        onItemTap: @escaping (TimetableItemWithFavorite) -> Void,
        onFavoriteTap: @escaping (TimetableItemWithFavorite, CGPoint?) -> Void
    ) {
        self.timeGroup = timeGroup
        self.onItemTap = onItemTap
        self.onFavoriteTap = onFavoriteTap
    }
    
    public var body: some View {
        HStack(alignment: .top, spacing: 12) {
            // Time display
            VStack(spacing: 2) {
                Text(timeGroup.startsTimeString)
                    .font(Typography.labelMedium)
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)

                Rectangle()
                    .fill(AssetColors.outline.swiftUIColor)
                    .frame(width: 2, height: 8)

                Text(timeGroup.endsTimeString)
                    .font(Typography.labelMedium)
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)
            }
            .frame(width: 45)
            .padding(.top, 4)
            
            // Session cards
            VStack(spacing: 12) {
                ForEach(timeGroup.items) { item in
                    TimetableCard(
                        timetableItem: item.timetableItem,
                        isFavorite: item.isFavorited,
                        onTap: { _ in
                            onItemTap(item)
                        },
                        onTapFavorite: { _, point in
                            onFavoriteTap(item, point)
                        }
                    )
                }
            }
            .frame(maxWidth: .infinity)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
    }
}