import SwiftUI

struct FloorMapSelector: View {
    @Binding var selected: FloorMap?
    
    var body: some View {
        HStack(spacing: 12) {
            ForEach(FloorMap.allCases, id: \.self) { floor in
                Button(action: {
                    selected = floor
                }) {
                    Text(floor.rawValue)
                        .font(.subheadline)
                        .fontWeight(selected == floor ? .semibold : .regular)
                        .foregroundColor(selected == floor ? .white : .primary)
                        .padding(.horizontal, 20)
                        .padding(.vertical, 8)
                        .background(
                            selected == floor ? Color.accentColor : Color.primary.opacity(0.1)
                        )
                        .cornerRadius(20)
                }
            }
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
    }
}