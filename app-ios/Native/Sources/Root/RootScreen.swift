import Dependencies
import SwiftUI
import HomeFeature

private enum TabType {
    case timeline
    case map
    case favorite
    case info
    case profileCard
}

public struct RootScreen: View {
    @Environment(\.scenePhase) private var scenePhase
    @State private var selectedTab: TabType = .timeline
    private let presenter = RootPresenter()

    public init() {}

    public var body: some View {
        TabView(selection: $selectedTab) {
            Tab("Timeline", systemImage: "calendar", value: .timeline) {
                HomeScreen()
            }
            Tab("Map", systemImage: "map", value: .map) {
                // TODO: Replace correct screen
                HomeScreen()
            }
            Tab("Favorite", systemImage: "heart", value: .favorite) {
                // TODO: Replace correct screen
                HomeScreen()
            }
            Tab("Info", systemImage: "info.circle", value: .info) {
                // TODO: Replace correct screen
                HomeScreen()
            }
            Tab("Profile Card", systemImage: "person.crop.square.on.square.angled", value: .info) {
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
