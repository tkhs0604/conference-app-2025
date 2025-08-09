import SwiftUI
import Theme
import Component

public struct ProfileCardScreen: View {
    @State private var presenter = ProfileCardPresenter()

    public init() {}

    public var body: some View {
        NavigationStack {
            profileCardScrollView
                .background(AssetColors.surface.swiftUIColor)
                .navigationTitle("Profile Card")
                #if os(iOS)
                    .navigationBarTitleDisplayMode(.large)
                #endif
        }
    }

    private var profileCardScrollView: some View {
        ScrollView {
            VStack(spacing: 0) {
                profileCard
                actionButtons
            }
            .padding(.vertical, 20)
            .padding(.bottom, 80)  // Tab bar padding
        }
    }

    private var profileCard: some View {
        VStack(spacing: 20) {
            avatarImage
            userInfoSection
            bioText
            qrCodeSection
        }
        .padding(24)
        .background(Color.primary.opacity(0.05))
        .cornerRadius(20)
        .shadow(color: .black.opacity(0.1), radius: 10, x: 0, y: 5)
        .padding(.horizontal, 56)
        .padding(.vertical, 32)
    }

    private var avatarImage: some View {
        Image(systemName: "person.circle.fill")
            .resizable()
            .frame(width: 120, height: 120)
            .foregroundColor(.accentColor)
    }

    private var userInfoSection: some View {
        VStack(spacing: 8) {
            Text(presenter.userName)
                .font(.title2)
                .fontWeight(.bold)

            Text(presenter.userRole)
                .font(.body)
                .foregroundColor(.secondary)

            Text(presenter.userCompany)
                .font(.caption)
                .foregroundColor(.secondary)
        }
    }

    private var bioText: some View {
        Text(presenter.userBio)
            .font(.body)
            .multilineTextAlignment(.center)
            .padding(.horizontal, 20)
    }

    private var qrCodeSection: some View {
        VStack(spacing: 8) {
            // TODO: Replace with actual QR code generation
            Image(systemName: "qrcode")
                .resizable()
                .frame(width: 150, height: 150)
                .foregroundColor(.primary.opacity(0.3))

            Text("Scan to connect")
                .font(.caption)
                .foregroundColor(.secondary)
        }
        .padding(.top, 8)
    }

    private var actionButtons: some View {
        VStack(spacing: 8) {
            shareButton
            editButton
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 12)
    }

    private var shareButton: some View {
        Button {
            presenter.shareProfileCard()
        } label: {
            Label("Share Profile Card", systemImage: "square.and.arrow.up")
                .frame(maxWidth: .infinity)
        }
        .filledButtonStyle()
    }

    private var editButton: some View {
        Button {
            presenter.editProfile()
        } label: {
            Text("Edit Profile")
                .frame(maxWidth: .infinity)
        }
        .textButtonStyle()
    }
}

#Preview {
    ProfileCardScreen()
}
