import Dependencies
import DependenciesMacros
import Model

@DependencyClient
public struct SponsorUseCase: Sendable {
    public var load: @Sendable () async -> [Model.SponsorCategory] = { [] }
}

public enum SponsorUseCaseKey: TestDependencyKey {
    public static let testValue = SponsorUseCase()
}

extension DependencyValues {
    public var sponsorUseCase: SponsorUseCase {
        get { self[SponsorUseCaseKey.self] }
        set { self[SponsorUseCaseKey.self] = newValue }
    }
}
