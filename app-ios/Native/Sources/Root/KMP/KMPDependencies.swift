import Dependencies

enum KMPDependencies {
    static func prepareKMPDependencies(_ dependencyValues: inout DependencyValues) {
        let timetableUseCaseImpl = TimetableUseCaseImpl()
        dependencyValues.timetableUseCase = .init(
            load: timetableUseCaseImpl.load,
            toggleFavorite: timetableUseCaseImpl.toggleFavorite
        )

        let sponsorUseCaseImpl = SponsorUseCaseImpl()
        dependencyValues.sponsorUseCase = .init(
            load: sponsorUseCaseImpl.load
        )

        let eventMapUseCaseImpl = EventMapUseCaseImpl()
        dependencyValues.eventMapUseCase = .init(
            load: eventMapUseCaseImpl.load
        )
    }
}
