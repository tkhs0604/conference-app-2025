import SwiftUI
import Theme

struct KeyVisual: View {
    var body: some View {
        VStack(spacing: 0) {
            // Header Logo - using system image as placeholder
            Image(systemName: "swift")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 200, height: 80)
                .foregroundColor(.accentColor)
                .padding(.bottom, 16)
            
            // Conference description text
            Text("DroidKaigi is a conference for Android developers")
                .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                .typographyStyle(.titleMedium)
                .multilineTextAlignment(.center)
                .padding(.bottom, 20)
            
            // Date and Location info container
            VStack(alignment: .leading, spacing: 12) {
                // Date row
                HStack(spacing: 8) {
                    Image("ic_schedule", bundle: .module)
                        .resizable()
                        .frame(width: 16, height: 16)
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                    
                    Text("Date:")
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                        .typographyStyle(.titleSmall)
                        .padding(.trailing, 4)
                    
                    Text("2025.09.12(Thu) - 13(Fri)")
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                        .typographyStyle(.titleSmall)
                    
                    Spacer()
                }
                
                // Location row
                HStack(spacing: 8) {
                    Image("ic_location_on", bundle: .module)
                        .resizable()
                        .frame(width: 16, height: 16)
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                    
                    Text("Location:")
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                        .typographyStyle(.titleSmall)
                        .padding(.trailing, 4)
                    
                    Text("Bellesalle Shibuya Garden")
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                        .typographyStyle(.titleSmall)
                    
                    Link(destination: URL(string: "https://goo.gl/maps/vv9sE19JvRjYKtSP9")!) {
                        Text("Check Map")
                            .typographyStyle(.titleSmall)
                            .foregroundStyle(AssetColors.jellyfish.swiftUIColor)
                            .underline()
                    }
                    
                    Spacer()
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, 20)
            .padding(.horizontal, 16)
            .background(AssetColors.surfaceContainerLow.swiftUIColor, in: RoundedRectangle(cornerRadius: 10))
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(style: StrokeStyle(lineWidth: 1, dash: [2, 2]))
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
            )
        }
    }
}
