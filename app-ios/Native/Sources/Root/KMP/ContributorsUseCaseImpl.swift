import Model
import UseCase
import shared

struct ContributorsUseCaseImpl {
    func load() -> any AsyncSequence<[Model.Contributor], Never> {
        let flow = KMPDependencyProvider.shared.appGraph.contributorsRepository
            .contributorsFlow()

        return flow.map { contributorsList in
            contributorsList.map { Model.Contributor(from: $0) }
        }
    }
}