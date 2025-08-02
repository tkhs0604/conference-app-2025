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
        HStack(alignment: .top, spacing: 16) {
            VStack {
                Text(timeGroup.startsTimeString)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)

                Text("|")
                    .font(.system(size: 8))
                    .foregroundStyle(AssetColors.outline.swiftUIColor)

                Text(timeGroup.endsTimeString)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                Spacer()
            }
            .frame(width: 50)
            
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