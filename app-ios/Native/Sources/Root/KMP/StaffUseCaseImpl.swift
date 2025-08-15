import Model
import UseCase
import shared

struct StaffUseCaseImpl {
    func load() -> any AsyncSequence<[Model.Staff], Never> {
        let flow = KMPDependencyProvider.shared.appGraph.staffRepository
            .staffFlow()

        return flow.map { staffList in
            staffList.map { Model.Staff(from: $0) }
        }
    }
}
