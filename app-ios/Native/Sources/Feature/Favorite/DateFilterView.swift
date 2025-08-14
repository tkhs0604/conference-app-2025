import SwiftUI
import Theme

struct DateFilterView : View {
    enum DateFilter: String, CaseIterable {
        case all = "すべて"
        case day1 = "9/12"
        case day2 = "9/13"
    }
    
    @Binding private var selectedDate: DateFilterView.DateFilter
    
    init(selectedDate: Binding<DateFilter>) {
        self._selectedDate = selectedDate
    }
    
    var body: some View {
        HStack(spacing: 8) {
            ForEach(DateFilter.allCases, id: \.self) { filter in
                Button(
                    action: {
                        selectedDate = filter
                    },
                    label: {
                        HStack(spacing: 4) {
                            if selectedDate == filter {
                                Image(systemName: "checkmark")
                                    .font(Typography.labelSmall)
                            }
                            Text(filter.rawValue)
                                .font(Typography.labelLarge)
                        }
                        .padding(.horizontal, 12)
                        .padding(.vertical, 6)
                        .background(
                            selectedDate == filter
                            ? AssetColors.secondaryContainer.swiftUIColor
                            : Color.clear
                        )
                        .foregroundStyle(
                            selectedDate == filter
                            ? AssetColors.onSecondaryContainer.swiftUIColor
                            : AssetColors.onSurfaceVariant.swiftUIColor
                        )
                        .overlay(
                            RoundedRectangle(cornerRadius: 8)
                                .stroke(
                                    selectedDate == filter
                                    ? Color.clear
                                    : AssetColors.outline.swiftUIColor,
                                    lineWidth: 1
                                )
                        )
                        .cornerRadius(8)
                    }
                )
                .buttonStyle(PlainButtonStyle())
            }
            Spacer()
        }
    }
}
