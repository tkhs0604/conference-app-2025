import Dependencies

enum KMPDependencies {
    static func prepareKMPDependencies(_ dependencyValues: inout DependencyValues) {
        let timetableUseCaseImpl = TimetableUseCaseImpl()
        let eventMapUseCaseImpl = EventMapUseCaseImpl()
        dependencyValues.timetableUseCase = .init(
            load: timetableUseCaseImpl.load
        )
        dependencyValues.eventMapUseCase = .init(
            load: eventMapUseCaseImpl.load
        )
    }
}
