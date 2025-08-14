import SwiftUI
import Theme

struct EmptyFavoritesView: View {
    var body: some View {
        VStack(spacing: 24) {
            Image(systemName: "heart.fill")
                .resizable()
                .frame(width: 36, height: 36)
                .foregroundStyle(AssetColors.primaryFixed.swiftUIColor)
                .padding(24)
                .background(
                    RoundedRectangle(cornerRadius: 24)
                        .fill(AssetColors.onPrimary.swiftUIColor)
                )

            VStack(spacing: 8) {
                Text("登録されたセッションが\nありません", bundle: .module)
                    .font(Typography.titleLarge)
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                    .multilineTextAlignment(.center)

                Text("気になるセッションをお気に入り登録しましょう", bundle: .module)
                    .font(Typography.bodyMedium)
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                    .multilineTextAlignment(.center)
                    .padding(.horizontal, 40)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
