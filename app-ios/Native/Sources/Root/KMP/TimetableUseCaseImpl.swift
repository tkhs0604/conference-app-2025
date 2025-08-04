import UseCase
import Model
import shared

struct TimetableUseCaseImpl {
    func load() async throws(LoadTimetableError) -> Model.Timetable {
        let iterator = KMPDependencyProvider.shared.appGraph.sessionsRepository
            .timetableFlow()
            .makeAsyncIterator()
        
        if let kmpTimetable = await iterator.next() {
            return Model.Timetable(from: kmpTimetable)
        } else {
            throw LoadTimetableError.networkError
        }
    }
}
