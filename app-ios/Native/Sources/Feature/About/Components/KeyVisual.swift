import SwiftUI

struct KeyVisual: View {
    var body: some View {
        VStack(spacing: 16) {
            // TODO: Replace with actual DroidKaigi logo asset when available
            Image(systemName: "swift")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 200, height: 80)
                .foregroundColor(.accentColor)
            
            VStack(spacing: 4) {
                HStack(spacing: 4) {
                    // TODO: Replace with actual location icon asset when available
                    Image(systemName: "location.fill")
                        .frame(width: 18, height: 18)
                    Text("Bellesalle Shibuya Garden")
                        .font(.caption)
                }
                
                HStack(spacing: 4) {
                    // TODO: Replace with actual schedule icon asset when available
                    Image(systemName: "calendar")
                        .frame(width: 18, height: 18)
                    Text("2025.09.12(Thu) - 13(Fri)")
                        .font(.caption)
                }
            }
            .foregroundColor(.secondary)
        }
    }
}