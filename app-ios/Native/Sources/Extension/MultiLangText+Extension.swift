import Foundation
import Model

extension MultiLangText {
    public var currentLangTitle: String {
        // For now, return English title by default
        // TODO: Implement proper language detection based on device settings
        return enTitle
    }
}
