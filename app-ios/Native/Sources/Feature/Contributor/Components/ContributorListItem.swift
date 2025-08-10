import SwiftUI
import Theme

struct ContributorListItem: View {
    let contributor: Contributor

    var body: some View {
        HStack(spacing: 16) {
            // TODO: Replace with actual contributor avatar when available
            Image(systemName: "person.circle.fill")
                .resizable()
                .frame(width: 56, height: 56)
                .foregroundColor(AssetColors.onSurface.swiftUIColor.opacity(0.6))

            Text(contributor.name)
                .font(.body)
                .foregroundColor(AssetColors.onSurface.swiftUIColor)

            Spacer()
        }
        .padding(.vertical, 12)
    }
}
