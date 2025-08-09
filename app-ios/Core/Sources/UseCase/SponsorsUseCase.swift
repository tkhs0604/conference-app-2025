import Dependencies
import DependenciesMacros
import Model

@DependencyClient
public struct SponsorsUseCase: Sendable {
    public var load: @Sendable () -> any AsyncSequence<[Model.Sponsor], Never> = { AsyncStream.never }
}

public enum SponsorsUseCaseKey: TestDependencyKey {
    public static let testValue = SponsorsUseCase()
}

extension DependencyValues {
    public var sponsorsUseCase: SponsorsUseCase {
        get { self[SponsorsUseCaseKey.self] }
        set { self[SponsorsUseCaseKey.self] = newValue }
    }
}