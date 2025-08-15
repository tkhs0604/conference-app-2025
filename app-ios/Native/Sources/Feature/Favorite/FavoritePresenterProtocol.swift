import Model

public protocol FavoritePresenterProtocol {
    var favoriteTimetableItems: [TimetableTimeGroupItems] { get }
    func loadInitial()
    func toggleFavorite(_ item: TimetableItemWithFavorite)
}
