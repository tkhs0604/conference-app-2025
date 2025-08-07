import Model
import Observation
import Presentation
import Foundation

@MainActor
@Observable
final class EventMapPresenter {
    let eventMap = EventMapProvider()
    var selectedFloorMap: FloorMap = .first
    
    init() {}
    
    func loadInitial() async {
        await eventMap.fetchEvents()
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
    case first = "1F"
    case b1f = "B1F"
    
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
