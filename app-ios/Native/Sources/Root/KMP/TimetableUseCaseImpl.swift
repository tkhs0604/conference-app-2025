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

    func toggleFavorite(_ itemId: Model.TimetableItemId) async {
        do {
            try await KMPDependencyProvider.shared.appGraph.sessionsRepository
                .toggleFavorite(timetableItemId: .init(value: itemId.value))
        } catch {
            print("Failed to toggle favorite: \(error)")
        }
    }
}
