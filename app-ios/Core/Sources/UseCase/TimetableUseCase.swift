import Dependencies
import DependenciesMacros
import Model

@DependencyClient
public struct TimetableUseCase: Sendable {
    public var load: @Sendable () -> any AsyncSequence<Model.Timetable, Never> = { AsyncStream.never }
}

public enum TimetableUseCaseKey: TestDependencyKey {
    public static let testValue: TimetableUseCase = TimetableUseCase()
}

extension DependencyValues {
    public var timetableUseCase: TimetableUseCase {
        get { self[TimetableUseCaseKey.self] }
        set { self[TimetableUseCaseKey.self] = newValue }
    }
}
