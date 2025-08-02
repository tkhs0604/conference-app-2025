import SwiftUI
import Model
import Presentation
import Component
import Theme

struct TimetableGridView: View {
    @Binding var selectedDay: DayTab
    let timetableItems: [TimetableTimeGroupItems]
    let rooms: [Room]
    let onItemTap: (TimetableItemWithFavorite) -> Void
    let isFavorite: (String) -> Bool
    
    var body: some View {
        ScrollView(.vertical) {
            VStack(spacing: 0) {
                DayTabBar(selectedDay: $selectedDay)
                    .padding(.horizontal)
                
                ScrollView(.horizontal) {
                    Grid(alignment: .leading, horizontalSpacing: 4, verticalSpacing: 2) {
                        // Header row with room names
                        GridRow {
                            Color.clear
                                .gridCellUnsizedAxes([.horizontal, .vertical])

                            ForEach(rooms, id: \.id) { room in
                                Text(room.displayName)
                                    .font(.system(size: 16, weight: .medium))
                                    .foregroundStyle(room.type.color)
                                    .frame(width: 192)
                            }
                        }

                        DashedDivider(axis: .horizontal)

                        // Time blocks with sessions
                        ForEach(timetableItems) { timeBlock in
                            GridRow {
                                // Time column
                                VStack {
                                    Text(timeBlock.startsTimeString)
                                        .font(.system(size: 12, weight: .medium))
                                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                                    Spacer()
                                }
                                .frame(width: 40, height: 153)

                                // Sessions for each room
                                if timeBlock.isLunchTime() {
                                    // Lunch spans all columns
                                    if let lunchItem = timeBlock.items.first {
                                        TimetableGridCard(
                                            timetableItem: lunchItem.timetableItem,
                                            cellCount: rooms.count,
                                            onTap: { item in
                                                onItemTap(
                                                    TimetableItemWithFavorite(
                                                        timetableItem: item,
                                                        isFavorited: isFavorite(item.id.value)
                                                    )
                                                )
                                            }
                                        )
                                        .gridCellColumns(rooms.count)
                                    }
                                } else {
                                    // Regular sessions
                                    ForEach(rooms, id: \.self) { room in
                                        if let item = timeBlock.getItem(for: room) {
                                            TimetableGridCard(
                                                timetableItem: item.timetableItem,
                                                cellCount: 1,
                                                onTap: { _ in
                                                    onItemTap(item)
                                                }
                                            )
                                        } else {
                                            Color.clear
                                                .frame(width: 192, height: 153)
                                        }
                                    }
                                }
                            }

                            DashedDivider(axis: .horizontal)
                        }
                    }
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.trailing)
                }
                
                // Bottom padding for tab bar
                Color.clear.padding(.bottom, 60)
            }
        }
    }
}