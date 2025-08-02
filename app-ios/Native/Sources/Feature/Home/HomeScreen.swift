import Dependencies
import Model
import Observation
import SwiftUI
import Theme
import Presentation

public struct HomeScreen: View {
    @State private var presenter = HomePresenter()
    @State private var animationProgress: CGFloat = 0
    @State private var targetTimetableItemId: String?
    @State private var targetLocationPoint: CGPoint?
    @State private var timetableMode: TimetableMode = .list
    @State private var selectedDay: DayTab = .day1

    public init() {}

    public var body: some View {
        let timetableItems = presenter.timetable.dayTimetable[selectedDay.model] ?? []

        NavigationStack {
            Group {
                switch timetableMode {
                case .list:
                    TimetableListView(
                        selectedDay: $selectedDay,
                        timetableItems: timetableItems,
                        onItemTap: { item in
                            presenter.timetableItemTapped(item)
                        },
                        onFavoriteTap: { item, _ in
                            presenter.timetable.toggleFavorite(item)
                        },
                        animationTrigger: { timetableItem, location in
                            toggleFavorite(timetableItem: timetableItem, adjustedLocationPoint: location)
                        }
                    )
                case .grid:
                    TimetableGridView(
                        selectedDay: $selectedDay,
                        timetableItems: timetableItems,
                        rooms: presenter.timetable.rooms,
                        onItemTap: { item in
                            presenter.timetableItemTapped(item)
                        },
                        isFavorite: { itemId in
                            presenter.timetable.isFavorite(itemId)
                        }
                    )
                }
            }
            .background(
                Image("background_night", bundle: .module)
                    .resizable()
                    .edgesIgnoringSafeArea(.all)
            )
            .navigationTitle("Timetable")
            .navigationBarTitleDisplayMode(.automatic)
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    HStack {
                        Button(action: {
                            presenter.searchTapped()
                        }) {
                            Image(systemName: "magnifyingglass")
                                .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                                .frame(width: 40, height: 40)
                        }
                        
                        Button(action: {
                            timetableMode = timetableMode == .list ? .grid : .list
                        }) {
                            Image(systemName: timetableMode == .list ? "square.grid.2x2" : "list.bullet")
                                .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                                .frame(width: 40, height: 40)
                        }
                    }
                }
            }
        }
        .toolbarBackground(.hidden, for: .navigationBar)
        .task {
            await presenter.loadInitial()
        }
    }
    
    private func toggleFavorite(timetableItem: any TimetableItem, adjustedLocationPoint: CGPoint?) {
        targetLocationPoint = adjustedLocationPoint
        targetTimetableItemId = timetableItem.id.value

        if targetTimetableItemId != nil {
            withAnimation(.easeOut(duration: 1)) {
                animationProgress = 1
            }
            Task {
                try await Task.sleep(nanoseconds: 1_000_000_000)
                targetTimetableItemId = nil
                targetLocationPoint = nil
                animationProgress = 0
            }
        }
    }
}

#Preview {
    HomeScreen()
}

private extension DayTab {
    var model: DroidKaigi2024Day {
        switch self {
        case .day1:
            .conferenceDay1
        case .day2:
            .conferenceDay2
        }
    }
}
