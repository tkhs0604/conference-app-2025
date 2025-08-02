import Model
import Observation
import Presentation
import Foundation

@MainActor
@Observable
final class FavoritePresenter {
    let timetable = TimetableProvider()
    
    var favoriteTimetableItems: [TimetableTimeGroupItems] {
        let favoriteItems = timetable.dayTimetable.values
            .flatMap { $0 }
            .flatMap { $0.items }
            .filter { timetable.favoriteIds.contains($0.timetableItem.id.value) }
        
        return sortListIntoTimeGroups(timetableItems: favoriteItems)
    }
    
    init() {}
    
    func loadInitial() async {
        await timetable.fetchTimetable()
    }
    
    func timetableItemTapped(_ item: TimetableItemWithFavorite) {
        print("Favorite item tapped: \(item.timetableItem.title)")
    }
    
    func toggleFavorite(_ item: TimetableItemWithFavorite) {
        timetable.toggleFavorite(item)
    }
    
    private func sortListIntoTimeGroups(timetableItems: [TimetableItemWithFavorite]) -> [TimetableTimeGroupItems] {
        let sortedItems: [(Date, Date, TimetableItemWithFavorite)] = timetableItems.map {
            ($0.timetableItem.startsAt, $0.timetableItem.endsAt, $0)
        }

        let myDict = sortedItems.reduce(into: [Date: TimetableTimeGroupItems]()) {
            if $0[$1.0] == nil {
                $0[$1.0] = TimetableTimeGroupItems(
                    startsTimeString:$1.0.formatted(.dateTime.hour(.twoDigits(amPM: .omitted)).minute()),
                    endsTimeString:$1.1.formatted(.dateTime.hour(.twoDigits(amPM: .omitted)).minute()),
                    items:[]
                )
            }
            $0[$1.0]?.items.append($1.2)
        }

        return myDict.values.sorted {
            $0.items[0].timetableItem.startsAt.timeIntervalSince1970 < $1.items[0].timetableItem.startsAt.timeIntervalSince1970
        }
    }
}