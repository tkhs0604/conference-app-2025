import Foundation
import Model
import Observation
import Presentation

@MainActor
@Observable
final class StaffPresenter {
    var staffList: [Staff] = []
    var isLoading = false

    init() {}

    func loadStaff() async {
        isLoading = true
        // TODO: Load actual staff data from API
        staffList = Staff.mocks
        isLoading = false
    }

    func staffTapped(_ staff: Staff) {
        // print("Staff tapped: \(staff.name)")
        // TODO: Open GitHub profile
    }
}

// Mock model - TODO: Replace with actual model from shared module
struct Staff: Identifiable {
    let id: String
    let name: String
    let role: String?
    let githubUsername: String
    let iconUrl: URL?

    var githubUrl: URL? {
        URL(string: "https://github.com/\(githubUsername)")
    }

    static let mocks: [Staff] = [
        Staff(
            id: "1",
            name: "Alice Johnson",
            role: "Organizer",
            githubUsername: "alice",
            iconUrl: URL(string: "https://picsum.photos/200/200?random=1")
        ),
        Staff(
            id: "2",
            name: "Bob Smith",
            role: "Speaker Coordinator",
            githubUsername: "bob_smith",
            iconUrl: URL(string: "https://picsum.photos/200/200?random=2")
        ),
        Staff(
            id: "3",
            name: "Charlie Brown",
            role: "Venue Manager",
            githubUsername: "charlie",
            iconUrl: URL(string: "https://picsum.photos/200/200?random=3")
        ),
        Staff(
            id: "4",
            name: "Diana Prince",
            role: "Volunteer Coordinator",
            githubUsername: "diana_p",
            iconUrl: nil
        ),
        Staff(
            id: "5",
            name: "Eve Wilson",
            role: "Tech Lead",
            githubUsername: "eve_w",
            iconUrl: URL(string: "https://picsum.photos/200/200?random=5")
        ),
        Staff(
            id: "6",
            name: "Frank Miller",
            role: "Design Lead",
            githubUsername: "frank_m",
            iconUrl: URL(string: "https://picsum.photos/200/200?random=6")
        ),
        Staff(
            id: "7",
            name: "Grace Lee",
            role: "Marketing",
            githubUsername: "grace_lee",
            iconUrl: nil
        ),
        Staff(
            id: "8",
            name: "Henry Davis",
            role: "Finance",
            githubUsername: "henry_d",
            iconUrl: URL(string: "https://picsum.photos/200/200?random=8")
        ),
    ]
}
