import Model
import Observation
import Presentation
import Foundation

@MainActor
@Observable
final class SearchPresenter {
    let timetable = TimetableProvider()
    
    var searchWord: String = ""
    var selectedDay: DroidKaigi2024Day?
    var selectedCategory: TimetableCategory?
    var selectedSessionType: TimetableSessionType?
    var selectedLanguage: TimetableLanguage?
    
    var filteredTimetableItems: [TimetableItemWithFavorite] {
        let allItems = timetable.dayTimetable.values
            .flatMap { $0 }
            .flatMap { $0.items }
        
        return allItems.filter { item in
            // Search word filter
            if !searchWord.isEmpty {
                let searchLower = searchWord.lowercased()
                let matchesTitle = item.timetableItem.title.ja.lowercased().contains(searchLower) ||
                                 item.timetableItem.title.en.lowercased().contains(searchLower)
                let matchesDescription = item.timetableItem.description?.ja.lowercased().contains(searchLower) ?? false ||
                                       item.timetableItem.description?.en.lowercased().contains(searchLower) ?? false
                
                if !matchesTitle && !matchesDescription {
                    return false
                }
            }
            
            // Day filter
            if let day = selectedDay {
                if item.timetableItem.day != day {
                    return false
                }
            }
            
            // Category filter
            if let category = selectedCategory {
                if item.timetableItem.category != category {
                    return false
                }
            }
            
            // Session type filter
            if let sessionType = selectedSessionType {
                if let itemSessionType = item.timetableItem.sessionType {
                    if itemSessionType != sessionType {
                        return false
                    }
                } else {
                    return false
                }
            }
            
            // Language filter
            if let language = selectedLanguage {
                if item.timetableItem.language != language {
                    return false
                }
            }
            
            return true
        }
    }
    
    init() {}
    
    func loadInitial() async {
        await timetable.fetchTimetable()
    }
    
    func toggleFavorite(_ itemId: TimetableItemId) {
        if let item = filteredTimetableItems.first(where: { $0.timetableItem.id == itemId }) {
            timetable.toggleFavorite(item)
        }
    }
    
    func timetableItemTapped(_ item: TimetableItemWithFavorite) {
        print("Search item tapped: \(item.timetableItem.title)")
    }
}