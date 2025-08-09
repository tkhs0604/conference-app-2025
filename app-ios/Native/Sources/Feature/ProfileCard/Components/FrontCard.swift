import SwiftUI
import Theme

struct FrontCard: View {
    let userRole: String
    let userName: String
    
    var body: some View {
        ZStack {
            Image("dark_background", bundle: .module)
                .resizable()
                .scaledToFill()
            VStack(alignment: .center, spacing: 20) {
                Image("card_title", bundle: .module)
                VStack(alignment: .center, spacing: 12) {
                    avatarImage
                    VStack(alignment: .center, spacing: 0) {
                        Text(userRole)
                            .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                            .typographyStyle(.bodyMedium)
                        Text(userName)
                            .foregroundStyle(.white)
                            .typographyStyle(.headlineSmall)
                    }
                }
            }
            .padding(.horizontal, 30)
            .padding(.vertical, 40)
            VStack {
                Spacer()
                Image("dark_wave", bundle: .module)
            }
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
    
    // TODO: Replace user image
    private var avatarImage: some View {
        Image(systemName: "person.circle.fill")
            .resizable()
            .frame(width: 131, height: 131)
            .foregroundColor(.accentColor)
    }
}
