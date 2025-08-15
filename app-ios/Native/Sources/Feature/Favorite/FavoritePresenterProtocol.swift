import Model

public protocol FavoritePresenterProtocol {
    var favoriteTimetableItems: [TimetableTimeGroupItems] { get }
    var dateFilter: FavoriteDateFilter { get set }
    func loadInitial()
    func toggleFavorite(_ item: TimetableItemWithFavorite)
}
