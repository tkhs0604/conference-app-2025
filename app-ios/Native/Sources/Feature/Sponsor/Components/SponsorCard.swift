import Model
import SwiftUI
import Theme

struct SponsorCard: View {
    let sponsor: Model.Sponsor
    let tier: Model.SponsorCategory.SponsorTier

    var cardHeight: CGFloat {
        switch tier {
        case .platinum:
            return 110
        case .gold:
            return 80
        case .supporters:
            return 80
        }
    }

    var body: some View {
        RoundedRectangle(cornerRadius: 8)
            .fill(AssetColors.surface.swiftUIColor)
            .frame(height: cardHeight)
            .overlay(
                VStack(spacing: 8) {
                    // TODO: Replace with actual sponsor logo when available
                    Image(systemName: "building.2.crop.circle.fill")
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(height: cardHeight * 0.3)
                        .foregroundColor(.secondary.opacity(0.3))

                    Text(sponsor.name)
                        .font(.system(size: tier == .platinum ? 14 : 12))
                        .foregroundColor(.black)
                        .lineLimit(1)
                        .truncationMode(.tail)
                        .padding(.horizontal, 8)
                }
                .padding(.vertical, 8)
            )
    }
}

struct SponsorSection: View {
    let category: Model.SponsorCategory
    let onSponsorTapped: (Model.Sponsor) -> Void

    private var columns: [GridItem] {
        switch category.tier {
        case .platinum:
            return [GridItem(.flexible())]
        case .gold:
            return [
                GridItem(.flexible(), spacing: 12),
                GridItem(.flexible(), spacing: 12),
            ]
        case .supporters:
            return [
                GridItem(.flexible(), spacing: 12),
                GridItem(.flexible(), spacing: 12),
                GridItem(.flexible(), spacing: 12),
            ]
        }
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(category.name)
                .typographyStyle(.titleMedium)
                .foregroundStyle(AssetColors.primary90.swiftUIColor)
                .padding(.horizontal, 16)
                .padding(.top, 12)
                .padding(.bottom, 16)

            LazyVGrid(columns: columns, spacing: 12) {
                ForEach(category.sponsors) { sponsor in
                    Button(
                        action: {
                            onSponsorTapped(sponsor)
                        },
                        label: {
                            SponsorCard(
                                sponsor: sponsor,
                                tier: category.tier
                            )
                        }
                    )
                    .buttonStyle(PlainButtonStyle())
                }
            }
            .padding(.horizontal, 16)
            .padding(.bottom, 24)
        }
    }
}
