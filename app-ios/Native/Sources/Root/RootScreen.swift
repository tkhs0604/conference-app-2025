import Dependencies
import SwiftUI
import HomeFeature
import AboutFeature
import ContributorFeature
import EventMapFeature
import FavoriteFeature
import ProfileCardFeature
import SearchFeature
import SponsorFeature
import StaffFeature
import TimetableDetailFeature
import Model
import Theme

private enum TabType: CaseIterable, Hashable {
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
    @State private var navigationPath = NavigationPath()
    @State private var aboutNavigationPath = NavigationPath()
    @State private var favoriteNavigationPath = NavigationPath()
    @State private var composeMultiplatformEnabled: Bool = false
    private let presenter = RootPresenter()
    
    public init() {
        UITabBar.appearance().unselectedItemTintColor = UIColor(named: "tab_inactive")
    }
    
    public var body: some View {
        if (composeMultiplatformEnabled) {
            KmpAppComposeViewControllerWrapper()
                .environment(\.colorScheme, .dark)
                .ignoresSafeArea(.all)
        } else {
            ZStack(alignment: .bottom) {
                switch selectedTab {
                case .timetable:
                    NavigationStack(path: $navigationPath) {
                        HomeScreen(onNavigate: handleHomeNavigation)
                            .navigationDestination(for: NavigationDestination.self) { destination in
                                let navigationHandler = NavigationHandler(
                                    handleSearchNavigation: handleSearchNavigation
                                )
                                destination.view(with: navigationHandler)
                            }
                    }
                case .map:
                    NavigationStack {
                        EventMapScreen()
                    }
                case .favorite:
                    NavigationStack(path: $favoriteNavigationPath) {
                        FavoriteScreen(onNavigate: handleFavoriteNavigation)
                            .navigationDestination(for: FavoriteNavigationDestination.self) { destination in
                                switch destination {
                                case .timetableDetail(let item):
                                    TimetableDetailScreen(timetableItem: item)
                                }
                            }
                    }
                case .info:
                    NavigationStack(path: $aboutNavigationPath) {
                        AboutScreen(
                            onNavigate: handleAboutNavigation,
                            onEnableComposeMultiplatform: handleEnableComposeMultiplatform,
                        )
                        .navigationDestination(for: AboutNavigationDestination.self) { destination in
                            switch destination {
                            case .contributors:
                                ContributorScreen()
                            case .staff:
                                StaffScreen()
                            case .sponsors:
                                SponsorScreen()
                            }
                        }
                    }
                case .profileCard:
                    NavigationStack {
                        ProfileCardScreen()
                    }
                }
                
                tabBar
            }
            .onAppear {
                presenter.prepareWindow()
            }
            .onChange(of: scenePhase) {
                ScenePhaseHandler.handle(scenePhase)
            }
            .preferredColorScheme(.dark)
        }
    }
    
    private func handleHomeNavigation(_ destination: HomeNavigationDestination) {
        switch destination {
        case .timetableDetail(let item):
            navigationPath.append(NavigationDestination.timetableDetail(item))
        case .search:
            navigationPath.append(NavigationDestination.search)
        }
    }
    
    private func handleAboutNavigation(_ destination: AboutNavigationDestination) {
        aboutNavigationPath.append(destination)
    }
    
    private func handleFavoriteNavigation(_ destination: FavoriteNavigationDestination) {
        favoriteNavigationPath.append(destination)
    }
    
    private func handleSearchNavigation(_ destination: SearchNavigationDestination) {
        switch destination {
        case .timetableDetail(let item):
            navigationPath.append(NavigationDestination.timetableDetail(item))
        }
    }

    private func handleEnableComposeMultiplatform() {
        composeMultiplatformEnabled = true
    }
    
    @ViewBuilder
    private var tabBar: some View {
        GeometryReader { geometry in
            HStack(spacing: 0) {
                ForEach(TabType.allCases, id: \.self) { item in
                    let isSelected = selectedTab == item
                    Button {
                        selectedTab = item
                    } label: {
                        Image(item.tabImageName(selectedTab))
                            .renderingMode(.template)
                            .tint(isSelected ? AssetColors.primary40.swiftUIColor : AssetColors.onSurfaceVariant.swiftUIColor)
                            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                            .contentShape(Rectangle())
                    }
                    .frame(
                        maxWidth: geometry.size.width / CGFloat(TabType.allCases.count),
                        maxHeight: .infinity,
                        alignment: .center
                    )
                }
            }
            .frame(width: geometry.size.width, height: geometry.size.height)
        }
        .frame(height: 64)
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 12)
        .background(.ultraThinMaterial, in: Capsule())
        .overlay(Capsule().stroke(AssetColors.outline.swiftUIColor, lineWidth: 1))
        .environment(\.colorScheme, .dark)
        .padding(.horizontal, 48)
    }
}

#Preview {
    RootScreen()
}
