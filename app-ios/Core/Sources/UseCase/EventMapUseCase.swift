import Dependencies
import DependenciesMacros
import Model

public enum LoadEventError: Error {
    case networkError
}

@DependencyClient
public struct EventMapUseCase: Sendable {
    public var load: @Sendable () async throws(LoadEventError) -> [EventMapEvent] = { [] }
}

public enum EventMapUseCaseKey: TestDependencyKey {
    public static let testValue = EventMapUseCase()
}

extension DependencyValues {
    public var eventMapUseCase: EventMapUseCase {
        get { self[EventMapUseCaseKey.self] }
        set { self[EventMapUseCaseKey.self] = newValue }
    }
}
