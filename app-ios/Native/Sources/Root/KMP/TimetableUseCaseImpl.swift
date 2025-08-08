import Model
import UseCase
import shared

struct TimetableUseCaseImpl {
    func load() -> any AsyncSequence<Model.Timetable, Never> {
        let flow = KMPDependencyProvider.shared.appGraph.sessionsRepository
            .timetableFlow()

        return flow.map {
            .init(from: $0)
        }
    }
}
