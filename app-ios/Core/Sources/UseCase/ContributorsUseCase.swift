import Dependencies
import DependenciesMacros
import Model

@DependencyClient
public struct ContributorsUseCase: Sendable {
    public var load: @Sendable () -> any AsyncSequence<[Model.Contributor], Never> = { AsyncStream.never }
}

public enum ContributorsUseCaseKey: TestDependencyKey {
    public static let testValue = ContributorsUseCase()
}

extension DependencyValues {
    public var contributorsUseCase: ContributorsUseCase {
        get { self[ContributorsUseCaseKey.self] }
        set { self[ContributorsUseCaseKey.self] = newValue }
    }
}