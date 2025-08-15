import Foundation
import Model
import Observation
import Presentation

@MainActor
@Observable
final class EventMapPresenter {
    let eventMap = EventMapProvider()
    var selectedFloorMap: FloorMap = .first

    init() {}

    func loadInitial() {
        eventMap.subscribeEventMapEventsIfNeeded()
    }

    func selectFloorMap(_ floorMap: FloorMap) {
        selectedFloorMap = floorMap
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
