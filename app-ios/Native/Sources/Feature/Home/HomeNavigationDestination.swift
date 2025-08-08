import Model

public enum HomeNavigationDestination: Hashable {
    case timetableDetail(TimetableItemWithFavorite)
    case search
}