import Presentation
import SwiftUI

struct DayTabBar: View {
    @Binding var selectedDay: DayTab

    var body: some View {
        Picker("Day", selection: $selectedDay) {
            ForEach(DayTab.allCases) { day in
                Text(day.rawValue).tag(day)
            }
        }
        .pickerStyle(SegmentedPickerStyle())
        .padding(.vertical, 8)
    }
}
