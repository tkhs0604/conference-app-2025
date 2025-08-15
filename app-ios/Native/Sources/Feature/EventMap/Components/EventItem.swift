import Component
import Model
import SwiftUI
import Theme

struct EventItem: View {
    let event: EventMapEvent
    let onMoreDetailTapped: (URL) -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack(spacing: 12) {
                RoomTag(room: event.room)
                Text(event.name.currentLangTitle)
                    .font(Typography.titleMedium)
                    .foregroundColor(AssetColors.primaryFixed.swiftUIColor)
            }

            Text(event.description.currentLangTitle)
                .font(Typography.bodyLarge)
                .foregroundColor(AssetColors.onSurfaceVariant.swiftUIColor)
                .fixedSize(horizontal: false, vertical: true)

            if let message = event.message {
                Text(message.currentLangTitle)
                    .font(Typography.bodyMedium)
                    // FIXME: Failed to add color and run swiftgen
                    //                    .foregroundColor(AssetColors.tertiayFixedDim.swiftUIColor)
                    .fixedSize(horizontal: false, vertical: true)
            }

            if let url = event.moreDetailUrl {
                Button(action: {
                    onMoreDetailTapped(url)
                }) {
                    Text("詳しく見る")
                        .font(Typography.labelLarge)
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
