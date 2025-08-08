import SwiftUI

public extension View {
    func modifier(@ViewBuilder _ closure: (Self) -> some View) -> some View {
        closure(self)
    }
}
