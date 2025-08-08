import Foundation

enum DayTab: String, CaseIterable, Identifiable {
    case day1 = "Day 1"
    case day2 = "Day 2"
    
    var id: String { rawValue }
}