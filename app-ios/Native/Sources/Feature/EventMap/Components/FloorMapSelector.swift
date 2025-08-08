import SwiftUI
import Theme

struct FloorMapSelector: View {
    @Binding var selected: FloorMap?
    
    var body: some View {
        HStack(spacing: 6) {
            ForEach(FloorMap.allCases, id: \.self) { floor in
                FloorMapButton(floor: floor, isSelected: selected == floor) {
                    selected = floor
                }
            }
        }
        .padding(.horizontal, 16)
        .padding(.top, 8)
        .padding(.bottom, 12)
    }
}

private struct FloorMapButton: View {
    let floor: FloorMap
    let isSelected: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: 8) {
                if isSelected {
                    Image(systemName: "checkmark")
                }
                Text(floor.rawValue)
            }
        }
        .font(Typography.labelLarge)
        .fontWeight(isSelected ? .semibold : .regular)
        .foregroundColor(isSelected ? AssetColors.onSecondaryContainer.swiftUIColor : AssetColors.onSurface.swiftUIColor)
        .padding(.vertical, 6)
        .padding(.trailing, 16)
        .padding(.leading, isSelected ? 8 : 16)
        .background(isSelected ? AssetColors.secondaryContainer.swiftUIColor : .clear)
        .overlay {
            RoundedRectangle(cornerRadius: 8)
                .stroke(
                    isSelected ? .clear : AssetColors.outline.swiftUIColor,
                    lineWidth: 1,
                )
        }
        .cornerRadius(8)
        .buttonStyle(.plain)
    }
}

#Preview {
    FloorMapSelector(selected: .init(get: { .first }, set: { _ in }))
}
