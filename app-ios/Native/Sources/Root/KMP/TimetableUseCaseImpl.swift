import UseCase
import Foundation
import Model
import shared

struct TimetableUseCaseImpl {
    func load() async throws(LoadTimetableError) -> Model.Timetable {
        do {
            let response = try await KMPDependencyProvider.shared.appGraph.sessionsApiClient.sessionsAllResponse()
            let kmpTimetable = response.toTimetable()
            return Model.Timetable(from: kmpTimetable)
        } catch {
            throw LoadTimetableError.networkError
        }
    }
}
