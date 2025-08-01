import SwiftUI
import Model
import TimetableDetailFeature
import SearchFeature
import ContributorFeature
import StaffFeature
import SponsorFeature

// Navigation destinations for the entire app
public enum NavigationDestination: Hashable {
    case timetableDetail(TimetableItemWithFavorite)
    case search
    case contributors
    case staff
    case sponsors
}

// Navigation handler that can be passed down
public struct NavigationHandler {
    let handleSearchNavigation: (SearchNavigationDestination) -> Void
    
    public init(handleSearchNavigation: @escaping (SearchNavigationDestination) -> Void) {
        self.handleSearchNavigation = handleSearchNavigation
    }
}

// Extension to create views from navigation destinations
extension NavigationDestination {
    @ViewBuilder
    @MainActor
    func view(with navigationHandler: NavigationHandler) -> some View {
        switch self {
        case .timetableDetail(let item):
            TimetableDetailScreen(timetableItem: item)
        case .search:
            SearchScreen(onNavigate: navigationHandler.handleSearchNavigation)
        case .contributors:
            ContributorScreen()
        case .staff:
            StaffScreen()
        case .sponsors:
            SponsorScreen()
        }
    }
}