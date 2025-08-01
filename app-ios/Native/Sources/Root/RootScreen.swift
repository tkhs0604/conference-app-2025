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
    private let presenter = RootPresenter()
    
    public init() {
        UITabBar.appearance().unselectedItemTintColor = UIColor(named: "tab_inactive")
    }
    
    public var body: some View {
        NavigationStack(path: $navigationPath) {
            Group {
                if #available(iOS 26, *) {
                    TabView(selection: $selectedTab) {
                        Tab("Timetable",
                            image: TabType.timetable.tabImageName(selectedTab),
                            value: .timetable
                        ) {
                            HomeScreen(onNavigate: handleHomeNavigation)
                        }
                        Tab("Map",
                            image: TabType.map.tabImageName(selectedTab),
                            value: .map
                        ) {
                            EventMapScreen()
                        }
                        Tab("Favorite",
                            image: TabType.favorite.tabImageName(selectedTab),
                            value: .favorite
                        ) {
                            FavoriteScreen(onNavigate: handleFavoriteNavigation)
                        }
                        Tab("Info",
                            image: TabType.info.tabImageName(selectedTab),
                            value: .info
                        ) {
                            AboutScreen(onNavigate: handleAboutNavigation)
                        }
                        Tab("Profile Card",
                            image: TabType.profileCard.tabImageName(selectedTab),
                            value: .profileCard
                        ) {
                            ProfileCardScreen()
                        }
                    }
                } else {
                    ZStack(alignment: .bottom) {
                        switch selectedTab {
                        case .timetable:
                            HomeScreen(onNavigate: handleHomeNavigation)
                        case .map:
                            EventMapScreen()
                        case .favorite:
                            FavoriteScreen(onNavigate: handleFavoriteNavigation)
                        case .info:
                            AboutScreen(onNavigate: handleAboutNavigation)
                        case .profileCard:
                            ProfileCardScreen()
                        }
                        tabBar
                    }
                }
            }
            .navigationDestination(for: NavigationDestination.self) { destination in
                let navigationHandler = NavigationHandler(
                    handleSearchNavigation: handleSearchNavigation
                )
                destination.view(with: navigationHandler)
            }
        }
        .onAppear {
            presenter.prepareWindow()
        }
        .onChange(of: scenePhase) {
            ScenePhaseHandler.handle(scenePhase)
        }
        .preferredColorScheme(.dark)
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
        switch destination {
        case .contributors:
            navigationPath.append(NavigationDestination.contributors)
        case .staff:
            navigationPath.append(NavigationDestination.staff)
        case .sponsors:
            navigationPath.append(NavigationDestination.sponsors)
        }
    }
    
    private func handleFavoriteNavigation(_ destination: FavoriteNavigationDestination) {
        switch destination {
        case .timetableDetail(let item):
            navigationPath.append(NavigationDestination.timetableDetail(item))
        }
    }
    
    private func handleSearchNavigation(_ destination: SearchNavigationDestination) {
        switch destination {
        case .timetableDetail(let item):
            navigationPath.append(NavigationDestination.timetableDetail(item))
        }
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
                            .tint(isSelected ? .accentColor : Color("tab_inactive"))
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
        .overlay(Capsule().stroke(.gray, lineWidth: 1))
        .environment(\.colorScheme, .dark)
        .padding(.horizontal, 48)
    }
}

#Preview {
    RootScreen()
}
