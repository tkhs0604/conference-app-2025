import Model
import UseCase
import shared

struct SponsorsUseCaseImpl {
    func load() -> any AsyncSequence<[Model.Sponsor], Never> {
        let flow = KMPDependencyProvider.shared.appGraph.sponsorsRepository
            .sponsorsFlow()

        return flow.map { sponsorsList in
            sponsorsList.map { Model.Sponsor(from: $0) }
        }
    }
}