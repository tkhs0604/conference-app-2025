import Foundation
import Model
import Observation
import Presentation

@MainActor
@Observable
final class HomePresenter {
    let timetable = TimetableProvider()

    init() {}

    func loadInitial() {
        timetable.subscribeTimetableIfNeeded()
    }

    func timetableItemTapped(_ item: TimetableItemWithFavorite) {
        print("Tapped: \(item.timetableItem.title)")
    }

    func searchTapped() {
        print("Search tapped")
    }
}
