import Dependencies
import SwiftUI
import shared

@MainActor
struct KmpAppComposeViewControllerWrapper: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        shared.kaigiAppViewController(appGraph: KMPDependencyProvider.shared.appGraph)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
