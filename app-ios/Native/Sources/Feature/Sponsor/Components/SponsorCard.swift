import SwiftUI

struct SponsorCard: View {
    let sponsor: Sponsor
    let cardSize: CGSize

    var body: some View {
        VStack {
            // TODO: Replace with actual sponsor logo when available
            Image(systemName: "building.2.crop.circle.fill")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: cardSize.width * 0.6, height: cardSize.height * 0.5)
                .foregroundColor(.secondary.opacity(0.6))

            Text(sponsor.name)
                .font(.caption)
                .fontWeight(.medium)
                .multilineTextAlignment(.center)
                .foregroundColor(.primary)
                .padding(.horizontal, 8)
                .lineLimit(2)
        }
        .frame(width: cardSize.width, height: cardSize.height)
        .background(Color.primary.opacity(0.05))
        .cornerRadius(12)
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(Color.primary.opacity(0.1), lineWidth: 1)
        )
    }
}

struct SponsorSection: View {
    let category: SponsorCategory
    let onSponsorTapped: (Sponsor) -> Void

    private let columns = [
        GridItem(.adaptive(minimum: 140, maximum: 180), spacing: 16)
    ]

    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text(category.name)
                .font(.headline)
                .fontWeight(.bold)
                .foregroundColor(.primary)
                .padding(.horizontal, 16)

            LazyVGrid(columns: columns, spacing: 16) {
                ForEach(category.sponsors) { sponsor in
                    Button(action: {
                        onSponsorTapped(sponsor)
                    }) {
                        SponsorCard(
                            sponsor: sponsor,
                            cardSize: CGSize(width: 160, height: 120)
                        )
                    }
                    .buttonStyle(PlainButtonStyle())
                }
            }
            .padding(.horizontal, 16)
        }
    }
}
