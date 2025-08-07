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
            return "img_floorMap_light_B1F"
        case .first:
            return "img_floorMap_light_1F"
        }
    }
}
