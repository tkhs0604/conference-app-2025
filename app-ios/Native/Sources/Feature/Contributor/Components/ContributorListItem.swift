import SwiftUI
import Theme

struct ContributorListItem: View {
    let contributor: Contributor

    var body: some View {
        HStack(spacing: 16) {
            // TODO: Replace with actual contributor avatar when available
            Image(systemName: "person.circle.fill")
                .resizable()
                .frame(width: 48, height: 48)
                .foregroundColor(AssetColors.onSurface.swiftUIColor.opacity(0.6))

            VStack(alignment: .leading, spacing: 4) {
                Text(contributor.name)
                    .font(.body)
                    .foregroundColor(AssetColors.onSurface.swiftUIColor)

                Text("@\(contributor.githubUsername)")
                    .font(.caption)
                    .foregroundColor(AssetColors.onSurface.swiftUIColor.opacity(0.7))
            }

            Spacer()

            Image(systemName: "chevron.right")
                .font(.caption)
                .foregroundColor(AssetColors.onSurface.swiftUIColor.opacity(0.6))
        }
        .padding(.vertical, 8)
    }
}
