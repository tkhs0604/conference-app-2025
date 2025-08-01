import SwiftUI

struct SearchFilterChip<T: Hashable>: View {
    let title: String
    let isSelected: Bool
    let onTap: () -> Void
    
    var body: some View {
        Button(action: onTap) {
            HStack(spacing: 4) {
                Text(title)
                    .font(.caption)
                    .foregroundColor(isSelected ? .white : .primary)
                
                if isSelected {
                    Image(systemName: "xmark.circle.fill")
                        .font(.caption2)
                        .foregroundColor(.white.opacity(0.8))
                }
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 6)
            .background(
                isSelected ? Color.accentColor : Color.primary.opacity(0.1)
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
                .foregroundColor(.secondary)
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