import Foundation
import Model

public extension MultiLangText {
    var currentLangTitle: String {
        // For now, return English title by default
        // TODO: Implement proper language detection based on device settings
        enTitle
    }
}
