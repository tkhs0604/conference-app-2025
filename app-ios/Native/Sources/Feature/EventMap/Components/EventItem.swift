import SwiftUI
import Model
import Theme

struct EventItem: View {
    let event: Event
    let onMoreDetailTapped: (URL) -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(event.title)
                .font(.headline)
                .foregroundColor(AssetColors.primaryFixed.swiftUIColor)
            
            Text(event.description)
                .font(.body)
                .foregroundColor(AssetColors.onSurfaceVariant.swiftUIColor)
                .fixedSize(horizontal: false, vertical: true)
            
            if let url = event.moreDetailUrl {
                Button(action: {
                    onMoreDetailTapped(url)
                }) {
                    Text("詳しく見る")
                        .font(.subheadline)
                        .foregroundColor(AssetColors.primaryFixed.swiftUIColor)
                }
                .frame(maxWidth: .infinity)
                .padding(.vertical, 10)
                .overlay {
                    RoundedRectangle(cornerRadius: 100)
                        .stroke(style: StrokeStyle(lineWidth: 1))
                        .fill(AssetColors.outline.swiftUIColor)
                }
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(.all, 16)
    }
}
