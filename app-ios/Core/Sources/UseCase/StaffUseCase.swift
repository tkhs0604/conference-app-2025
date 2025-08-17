import Dependencies
import DependenciesMacros
import Model

@DependencyClient
public struct StaffUseCase: Sendable {
    public var load: @Sendable () -> any AsyncSequence<[Model.Staff], Never> = { AsyncStream.never }
}

public enum StaffUseCaseKey: TestDependencyKey {
    public static let testValue = StaffUseCase()
}

extension DependencyValues {
    public var staffUseCase: StaffUseCase {
        get { self[StaffUseCaseKey.self] }
        set { self[StaffUseCaseKey.self] = newValue }
    }
}
