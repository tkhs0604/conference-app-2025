import Dependencies
import FavoriteFeature
import Model
import SwiftUI
import UseCase
import shared

@MainActor
struct KMPFavoritesScreenViewControllerWrapper: UIViewControllerRepresentable {
    let onNavigate: (FavoriteNavigationDestination) -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        shared.KmpFavoritesScreenViewControllerKt.kmpFavoritesScreenViewController(
            appGraph: KMPDependencyProvider.shared.appGraph,
            onTimetableItemClick: { item in
                let iosTimetableItemWithFavorite = Model.TimetableItemWithFavorite(from: item)
                onNavigate(.timetableDetail(iosTimetableItemWithFavorite))
            },
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
