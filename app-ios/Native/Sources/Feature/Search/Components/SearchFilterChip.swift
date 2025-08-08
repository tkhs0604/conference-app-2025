import SwiftUI
import Theme

struct SearchFilterChip<T: Hashable>: View {
    let title: String
    let isSelected: Bool
    let onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            HStack(spacing: 4) {
                Text(title)
                    .font(.caption)
                    .foregroundStyle(
                        isSelected ? AssetColors.onPrimary.swiftUIColor : AssetColors.onSurface.swiftUIColor)

                if isSelected {
                    Image(systemName: "xmark.circle.fill")
                        .font(.caption2)
                        .foregroundStyle(AssetColors.onPrimary.swiftUIColor.opacity(0.8))
                }
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 6)
            .background(
                isSelected ? AssetColors.primary40.swiftUIColor : AssetColors.surfaceVariant.swiftUIColor
            )
            .cornerRadius(16)
        }
    }
}

struct SearchFilterSection<T: Hashable & CaseIterable & RawRepresentable>: View where T.RawValue == String {
    let title: String
    @Binding var selection: T?

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.caption)
                .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                .padding(.horizontal, 16)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 8) {
                    SearchFilterChip<T>(
                        title: "All",
                        isSelected: selection == nil,
                        onTap: {
                            selection = nil
                        }
                    )

                    ForEach(Array(T.allCases), id: \.self) { item in
                        SearchFilterChip<T>(
                            title: item.rawValue,
                            isSelected: selection == item,
                            onTap: {
                                selection = selection == item ? nil : item
                            }
                        )
                    }
                }
                .padding(.horizontal, 16)
            }
        }
    }
}
