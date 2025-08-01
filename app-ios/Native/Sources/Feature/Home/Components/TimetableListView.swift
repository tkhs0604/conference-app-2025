import SwiftUI
import Model
import Presentation
import Component

struct TimetableListView: View {
    @Binding var selectedDay: DayTab
    let timetableItems: [TimetableTimeGroupItems]
    let onItemTap: (TimetableItemWithFavorite) -> Void
    let onFavoriteTap: (TimetableItemWithFavorite, CGPoint?) -> Void
    let animationTrigger: (any TimetableItem, CGPoint?) -> Void
    
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 0) {
                DayTabBar(selectedDay: $selectedDay)
                    .padding(.horizontal)

                ForEach(timetableItems) { timeGroup in
                    TimeGroupList(
                        timeGroup: timeGroup,
                        onItemTap: { item in
                            onItemTap(item)
                        },
                        onFavoriteTap: { item, location in
                            onFavoriteTap(item, location)
                            
                            if !item.isFavorited {
                                animationTrigger(item.timetableItem, location)
                            }
                        }
                    )
                }
            }
            .padding(.bottom, 60)
        }
    }
}
