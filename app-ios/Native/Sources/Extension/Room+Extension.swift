import Model
import SwiftUI

public struct RoomTheme {
    public let primaryColor: Color
    public let containerColor: Color
}

public extension Room {
    var color: Color {
        switch type {
        case .roomF:
            return Color.blue
        case .roomG:
            return Color.green
        case .roomH:
            return Color.orange
        case .roomI:
            return Color.purple
        case .roomJ:
            return Color.red
        case .roomIJ:
            return Color.indigo
        }
    }

    var roomTheme: RoomTheme {
        RoomTheme(
            primaryColor: color,
            containerColor: color.opacity(0.1)
        )
    }

    var displayName: String {
        Locale.current.identifier == "ja-JP"
            ? name.jaTitle
            : name.enTitle
    }

    // For backward compatibility with old code
    var rawValue: String {
        displayName
    }
}

public extension Speaker {
    // For backward compatibility
    var imageUrl: String? {
        iconUrl
    }
}
