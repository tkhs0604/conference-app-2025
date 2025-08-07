import SwiftUI

struct EventItem: View {
    let event: Event
    let onMoreDetailTapped: (URL) -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(event.title)
                .font(.headline)
                .foregroundColor(.primary)

            Text(event.description)
                .font(.body)
                .foregroundColor(.secondary)
                .fixedSize(horizontal: false, vertical: true)

            if let url = event.moreDetailUrl {
                Button(action: {
                    onMoreDetailTapped(url)
                }) {
                    HStack {
                        Text("More Details")
                            .font(.subheadline)
                        Image(systemName: "arrow.up.right.square")
                            .font(.caption)
                    }
                    .foregroundColor(.accentColor)
                }
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color.primary.opacity(0.05))
        .cornerRadius(12)
        .padding(.horizontal, 16)
    }
}
