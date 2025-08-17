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
    private let sponsorProvider = Presentation.SponsorProvider()
    
    init() {}

    func loadSponsors() async {
        await sponsorProvider.loadSponsorCategories()
    }

    func sponsorTapped(_ sponsor: Model.Sponsor) {
        // TODO: Open sponsor website
        #if os(iOS)
            UIApplication.shared.open(sponsor.websiteUrl)
        #endif
    }
}
