import Dependencies

enum KMPDependencies {
    static func prepareKMPDependencies(_ dependencyValues: inout DependencyValues) {
        let timetableUseCaseImpl = TimetableUseCaseImpl()
        dependencyValues.timetableUseCase = .init(
            load: timetableUseCaseImpl.load
        )

        let sponsorUseCaseImpl = SponsorUseCaseImpl()
        dependencyValues.sponsorUseCase = .init(
            load: sponsorUseCaseImpl.load
        )
    }
}
