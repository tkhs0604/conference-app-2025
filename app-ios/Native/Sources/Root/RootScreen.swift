import Dependencies
import SwiftUI
import HomeFeature

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
    private let presenter = RootPresenter()
    
    public init() {}
    
    public var body: some View {
        Group {
            if #available(iOS 26, *) {
                TabView(selection: $selectedTab) {
                    Tab("Timeline",
                        image: TabType.timetable.tabImageName(selectedTab),
                        value: .timetable
                    ) {
                        HomeScreen()
                    }
                    Tab("Map",
                        image: TabType.map.tabImageName(selectedTab),
                        value: .map
                    ) {
                        // TODO: Replace correct screen
                        HomeScreen()
                    }
                    Tab("Favorite",
                        image: TabType.favorite.tabImageName(selectedTab),
                        value: .favorite
                    ) {
                        // TODO: Replace correct screen
                        HomeScreen()
                    }
                    Tab("Info",
                        image: TabType.info.tabImageName(selectedTab),
                        value: .info
                    ) {
                        // TODO: Replace correct screen
                        HomeScreen()
                    }
                    Tab("Profile Card",
                        image: TabType.profileCard.tabImageName(selectedTab),
                        value: .profileCard
                    ) {
                        // TODO: Replace correct screen
                        HomeScreen()
                    }
                }
            } else {
                ZStack(alignment: .bottom) {
                    switch selectedTab {
                    case .timetable:
                        HomeScreen()
                    case .map:
                        HomeScreen()
                    case .favorite:
                        HomeScreen()
                    case .info:
                        HomeScreen()
                    case .profileCard:
                        HomeScreen()
                    }
                    tabBar
                }
            }
        }
        .onAppear {
            presenter.prepareWindow()
        }
        .onChange(of: scenePhase) {
            ScenePhaseHandler.handle(scenePhase)
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
                            .tint(isSelected ? .accentColor : .white)
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
