import Dependencies
import SwiftUI
import HomeFeature

private enum TabType {
    case timetable
    case map
    case favorite
    case info
    case profileCard

    internal func tabImageName(_ selectedTab: TabType) -> String {
        switch self {
        case .timetable:
            return selectedTab == self ? "ic_timetable.fill" : "ic_timetable"
        case .map:
            return selectedTab == self ? "ic_map.fill" : "ic_map"
        case .favorite:
            return selectedTab == self ? "ic_fav.fill" : "ic_fav"
        case .info:
            return selectedTab == self ? "ic_info.fill" : "ic_info"
        case .profileCard:
            return selectedTab == self ? "ic_profileCard.fill" : "ic_profileCard"
        }
    }
}

public struct RootScreen: View {
    @Environment(\.scenePhase) private var scenePhase
    @State private var selectedTab: TabType = .timetable
    private let presenter = RootPresenter()

    public init() {}

    public var body: some View {
        TabView(selection: $selectedTab) {
            Tab("Timeline", image: TabType.timetable.tabImageName(selectedTab), value: .timetable) {
                HomeScreen()
            }
            Tab("Map", image: TabType.map.tabImageName(selectedTab), value: .map) {
                // TODO: Replace correct screen
                HomeScreen()
            }
            Tab("Favorite", image: TabType.favorite.tabImageName(selectedTab), value: .favorite) {
                // TODO: Replace correct screen
                HomeScreen()
            }
            Tab("Info", image: TabType.info.tabImageName(selectedTab), value: .info) {
                // TODO: Replace correct screen
                HomeScreen()
            }
            Tab("Profile Card", image: TabType.profileCard.tabImageName(selectedTab), value: .info) {
                // TODO: Replace correct screen
                HomeScreen()
            }
        }
        .onAppear {
            presenter.prepareWindow()
        }
        .onChange(of: scenePhase) {
            ScenePhaseHandler.handle(scenePhase)
        }
    }
}

#Preview {
    RootScreen()
}
