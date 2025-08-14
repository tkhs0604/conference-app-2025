@preconcurrency import shared
import Dependencies
import DependenciesMacros
import Model

@DependencyClient
public struct KMPAppGraphUseCase: Sendable {
    public var appGraph: @Sendable () throws -> shared.IosAppGraph
}

public enum KMPAppGraphUseCaseKey: TestDependencyKey, Sendable {
    public static let testValue = KMPAppGraphUseCase()
}

extension DependencyValues {
    public var kmpAppGraphUseCase: KMPAppGraphUseCase {
        get { self[KMPAppGraphUseCaseKey.self] }
        set { self[KMPAppGraphUseCaseKey.self] = newValue }
    }
}
