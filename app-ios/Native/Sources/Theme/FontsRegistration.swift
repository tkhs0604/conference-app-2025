import Foundation
import SwiftUI

// Registers custom fonts shipped in the Theme package bundle.
// This works with fonts managed under Resources/Fonts via SwiftGen/SwiftPM resources.
public enum ThemeFonts {
    public static func registerAll() {
        // If SwiftGen generated helpers are available, prefer them.
        // Otherwise, fall back to scanning the bundle for .ttf/.otf files.
        #if canImport(UIKit)
            // Attempt to call generated API if present at link time.
            // Note: Even if not present, the fallback scan below will still register fonts.
            _ = {
                // Try type lookup by name to avoid hard dependency.
                if let _ = NSClassFromString("AssetFonts") as? NSObject.Type {
                    // no-op
                }
            }()
        #endif

        registerByScanningBundle()
    }

    private static func registerByScanningBundle() {
        #if canImport(UIKit)
            let exts = ["ttf", "otf"]
            for ext in exts {
                if let urls = Bundle.module.urls(forResourcesWithExtension: ext, subdirectory: nil) {
                    for url in urls {
                        _ = registerFont(at: url)
                    }
                }
            }
        #endif
    }

    @discardableResult
    private static func registerFont(at url: URL) -> Bool {
        #if canImport(UIKit)
            var error: Unmanaged<CFError>?
            let success = CTFontManagerRegisterFontsForURL(url as CFURL, .process, &error)
            return success
        #else
            return false
        #endif
    }
}
