//
//  Component.swift
//  Native
//
//  Created by Ryoya Ito on 2025/06/28.
//

import SwiftUI

#if canImport(UIKit)
    import UIKit
    import SafariServices
#endif

#if canImport(UIKit)
    public struct SafariView: UIViewControllerRepresentable {
        let url: URL

        public init(url: URL) {
            self.url = url
        }

        public func makeUIViewController(context: Context) -> SFSafariViewController {
            return SFSafariViewController(url: url)
        }

        public func updateUIViewController(_ uiViewController: SFSafariViewController, context: Context) {}
    }
#else
    public struct SafariView: View {
        let url: URL

        public init(url: URL) {
            self.url = url
        }

        public var body: some View {
            Text("Safari View not available on this platform")
        }
    }
#endif
