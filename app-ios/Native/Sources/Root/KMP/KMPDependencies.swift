import Dependencies

enum KMPDependencies {
    static func prepareKMPDependencies(_ dependencyValues: inout DependencyValues) {
        let timetableUseCaseImpl = TimetableUseCaseImpl()
        let eventMapUseCaseImpl = EventMapUseCaseImpl()
        dependencyValues.timetableUseCase = .init(
            load: timetableUseCaseImpl.load
        )

        let sponsorUseCaseImpl = SponsorUseCaseImpl()
        dependencyValues.sponsorUseCase = .init(
            load: sponsorUseCaseImpl.load
        )

        let sponsorsUseCaseImpl = SponsorsUseCaseImpl()
        dependencyValues.sponsorsUseCase = .init(
            load: sponsorsUseCaseImpl.load
        )

        let staffUseCaseImpl = StaffUseCaseImpl()
        dependencyValues.staffUseCase = .init(
            load: staffUseCaseImpl.load
        )

        let contributorsUseCaseImpl = ContributorsUseCaseImpl()
        dependencyValues.contributorsUseCase = .init(
            load: contributorsUseCaseImpl.load
        )
        
        dependencyValues.eventMapUseCase = .init(
            load: eventMapUseCaseImpl.load
        )
    }
}
