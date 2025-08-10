import Dependencies
import Foundation
import Model
import Observation
import Presentation
import UseCase

#if os(iOS)
    import UIKit
#endif

@MainActor
@Observable
final class SponsorPresenter {
    @ObservationIgnored
    @Dependency(\.sponsorUseCase) private var sponsorUseCase

    var sponsors: [Model.SponsorCategory] = []
    var isLoading = false

    init() {}

    func loadSponsors() async {
        isLoading = true
        sponsors = await sponsorUseCase.load()
        isLoading = false
    }

    func sponsorTapped(_ sponsor: Model.Sponsor) {
        // TODO: Open sponsor website
        #if os(iOS)
            UIApplication.shared.open(sponsor.websiteUrl)
        #endif
    }
}
