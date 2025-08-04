import SwiftUI
import Theme

struct FloorMapSelector: View {
    @Binding var selected: FloorMap?
    
    var body: some View {
        HStack(spacing: 6) {
            ForEach(FloorMap.allCases, id: \.self) { floor in
                Button(action: {
                    selected = floor
                }) {
                    if (selected == floor) {
                        Label(floor.rawValue, systemImage: "checkmark")
                    } else {
                        Text(floor.rawValue)
                    }
                }
                .font(.subheadline)
                .fontWeight(selected == floor ? .semibold : .regular)
                .foregroundColor(selected == floor ? AssetColors.onSecondaryContainer.swiftUIColor : AssetColors.onSurface.swiftUIColor)
                .padding(.horizontal, 16)
                .padding(.vertical, 8)
                .background(selected == floor ? AssetColors.secondaryContainer.swiftUIColor : .clear)
                .cornerRadius(8)
                .overlay {
                    if (selected != floor) {
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(style: StrokeStyle(lineWidth: 1))
                            .fill(AssetColors.outline.swiftUIColor)
                    }
                }
            }
        }
        .padding(.horizontal, 16)
    }
}

#Preview {
    FloorMapSelector(selected: .init(get: { .first }, set: { _ in }))
}
