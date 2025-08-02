import Model
import Observation
import Presentation
import Foundation

@MainActor
@Observable
final class ContributorPresenter {
    var contributors: [Contributor] = []
    var isLoading = false
    
    init() {}
    
    func loadContributors() async {
        isLoading = true
        // TODO: Load actual contributor data from API or local storage
        // For now, using mock data
        contributors = [
            Contributor(id: "1", name: "Alice Developer", githubUsername: "alice_dev", iconUrl: nil),
            Contributor(id: "2", name: "Bob Engineer", githubUsername: "bob_eng", iconUrl: nil),
            Contributor(id: "3", name: "Charlie Designer", githubUsername: "charlie_des", iconUrl: nil),
        ]
        isLoading = false
    }
    
    func contributorTapped(_ contributor: Contributor) {
        print("Contributor tapped: \(contributor.name)")
        // TODO: Open GitHub profile
    }
}

// Mock contributor model - TODO: Replace with actual model from shared module
struct Contributor: Identifiable {
    let id: String
    let name: String
    let githubUsername: String
    let iconUrl: String?
}