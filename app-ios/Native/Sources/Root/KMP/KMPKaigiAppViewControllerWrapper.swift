import SwiftUI
import Dependencies
import shared

@MainActor
struct KmpAppComposeViewControllerWrapper: UIViewControllerRepresentable {
    init() {}

    func makeUIViewController(context: Context) -> UIViewController {
        shared.kaigiAppViewController(appGraph: KMPDependencyProvider.shared.appGraph)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
