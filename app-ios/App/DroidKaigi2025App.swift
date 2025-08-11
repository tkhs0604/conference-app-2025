//
//  DroidKaigi2025App.swift
//  DroidKaigi2025
//
//  Created by Ryoya Ito on 2025/06/28.
//

import Root
import Theme
import SwiftUI

@main
struct DroidKaigi2025App: App {
    init() {
        // Register custom fonts from Theme bundle so Font.custom can resolve them.
        ThemeFonts.registerAll()
    }
    var body: some Scene {
        WindowGroup {
            RootScreen()
        }
    }
}
