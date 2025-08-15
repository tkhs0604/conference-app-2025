import SwiftUI

struct FavoriteScreenUiModePicker : View {
    @Binding private var uiMode: UiMode
    
    enum UiMode: String, CaseIterable {
        case swiftui = "Swift UI"
        case cmp = "CMP"
        case kmpPresenter = "KMP Presenter"
    }
    
    init(uiMode: Binding<UiMode>) {
        self._uiMode = uiMode
    }
    
    var body: some View {
        Picker("UI Mode", selection: $uiMode) {
            ForEach(UiMode.allCases, id: \.self) { uiMode in
                Text(uiMode.rawValue)
            }
        }
    }
}
