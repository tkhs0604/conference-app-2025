import Dependencies
import shared
import SwiftUI
import UseCase

@MainActor
struct KMPFavoritesScreenViewControllerWrapper: UIViewControllerRepresentable {
    let onTimetableItemClick: (TimetableItemId) -> Void

    func makeUIViewController(context: Context) -> UIViewController {
        shared.KmpFavoritesScreenViewControllerKt.kmpFavoritesScreenViewController(
            appGraph: KMPDependencyProvider.shared.appGraph,
            onTimetableItemClick: { itemId in
                onTimetableItemClick(itemId)
            },
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
