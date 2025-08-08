import SwiftUI

struct ContributorListItem: View {
    let contributor: Contributor

    var body: some View {
        HStack(spacing: 16) {
            // TODO: Replace with actual contributor avatar when available
            Image(systemName: "person.circle.fill")
                .resizable()
                .frame(width: 48, height: 48)
                .foregroundColor(.secondary)

            VStack(alignment: .leading, spacing: 4) {
                Text(contributor.name)
                    .font(.body)
                    .foregroundColor(.primary)

                Text("@\(contributor.githubUsername)")
                    .font(.caption)
                    .foregroundColor(.secondary)
            }

            Spacer()

            Image(systemName: "chevron.right")
                .font(.caption)
                .foregroundColor(.secondary)
        }
        .padding(.vertical, 8)
    }
}
