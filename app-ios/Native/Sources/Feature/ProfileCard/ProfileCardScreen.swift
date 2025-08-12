import Component
import SwiftUI
import Theme

// TODO: fix actual model
enum ProfileCardType: String {
    case dark
    case light
}

public struct ProfileCardScreen: View {
    @State private var presenter = ProfileCardPresenter()
    @State private var cardType: ProfileCardType = .dark

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
        TiltFlipCard(front: { normal in
            FrontCard(
                userRole: presenter.userRole,
                userName: presenter.userName,
                cardType: cardType,
                normal: (Double(normal.x), Double(normal.y), Double(normal.z)),
            )
        }, back: { normal in
            BackCard(
                cardType: cardType,
                normal: (Double(normal.x), Double(normal.y), Double(normal.z)),
            )
        })
        .padding(.horizontal, 56)
        .padding(.vertical, 32)
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
            if cardType == .dark {
                cardType = .light
            } else {
                cardType = .dark
            }
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
