import Foundation
import Model
import UseCase
import shared

struct EventMapUseCaseImpl {
    func load() -> any AsyncSequence<[Model.EventMapEvent], Never> {
        let flow = KMPDependencyProvider.shared.appGraph.eventMapRepository.eventMapEventsFlow()
        return flow.map {
            $0.map {
                .init(from: $0)
            }
        }
    }
}
