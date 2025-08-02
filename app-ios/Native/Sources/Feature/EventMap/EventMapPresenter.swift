import Model
import Observation
import Presentation
import Foundation

@MainActor
@Observable
final class EventMapPresenter {
    var selectedFloorMap: FloorMap = .b1f
    var events: [Event] = []
    
    init() {}
    
    func loadInitial() {
        // TODO: Load actual event data
        events = Event.mockEvents
    }
    
    func selectFloorMap(_ floorMap: FloorMap) {
        selectedFloorMap = floorMap
    }
    
    func moreDetailButtonTapped(_ url: URL) {
        print("More detail tapped: \(url)")
        // TODO: Open in Safari
    }
}

// Mock models - TODO: Replace with actual models from shared module
enum FloorMap: String, CaseIterable {
    case b1f = "B1F"
    case first = "1F"
    
    var image: String {
        switch self {
        case .b1f:
            // TODO: Replace with actual B1F map image
            return "map"
        case .first:
            // TODO: Replace with actual 1F map image
            return "map.fill"
        }
    }
}

struct Event: Identifiable, Hashable {
    let id: String
    let title: String
    let description: String
    let moreDetailUrl: URL?
    
    static let mockEvents: [Event] = [
        Event(
            id: "1",
            title: "Welcome Talk",
            description: "Opening ceremony and keynote presentation",
            moreDetailUrl: URL(string: "https://droidkaigi.jp/2025/welcome-talk")
        ),
        Event(
            id: "2",
            title: "Party",
            description: "Networking party with food and drinks",
            moreDetailUrl: URL(string: "https://droidkaigi.jp/2025/party")
        ),
        Event(
            id: "3",
            title: "Ask the Speaker",
            description: "Q&A session with conference speakers",
            moreDetailUrl: URL(string: "https://droidkaigi.jp/2025/ask-speaker")
        ),
    ]
}