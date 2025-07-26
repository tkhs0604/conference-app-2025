import SwiftUI

extension View {
  /// For version branch
  func modifier(@ViewBuilder _ closure: (Self) -> some View) -> some View {
    closure(self)
  }
}
