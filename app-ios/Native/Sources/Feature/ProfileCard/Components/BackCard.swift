import SwiftUI
import Theme

struct BackCard: View {
    var body: some View {
        ZStack {
            Color(uiColor: UIColor(red: 40.0 / 255.0, green: 15.0 / 255.0, blue: 131.0 / 255.0, alpha: 1.0))
            Image("back_base", bundle: .module)
                .resizable()
                .scaledToFill()
            VStack(alignment: .center, spacing: 20) {
                Image("card_title", bundle: .module)
                // TODO: Replace with actual QR code generation
                QrCodeView(data: "sample")
                    .frame(width: 160, height: 160)
                Spacer()
            }
            .padding(.horizontal, 30)
            .padding(.vertical, 40)
            Image("back_front", bundle: .module)
                .resizable()
                .scaledToFill()
        }
        .frame(width: 300, height: 380)
        .cornerRadius(12)
        // Figma shadow: may be replaced by Metal shader
        .shadow(color: .black.opacity(0.12), radius: 10, x: 3, y: 3)
        .shadow(color: .black.opacity(0.11), radius: 17, x: 11, y: 14)
        .shadow(color: .black.opacity(0.06), radius: 24, x: 24, y: 31)
        .shadow(color: .black.opacity(0.02), radius: 28, x: 42, y: 55)
        .shadow(color: .black.opacity(0.00), radius: 31, x: 66, y: 87)
        .overlay {
            RoundedRectangle(cornerRadius: 12)
                .fill(.black.shadow(.inner(color: .white.opacity(0.32), radius: 2, x: -2, y: -2)))
                .rotationEffect(.degrees(180))
                .blendMode(.lighten)
        }
        .clipped(antialiased: true)
    }
}
