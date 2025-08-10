import SwiftUI
import Theme

public struct AboutScreen: View {
    @State private var presenter = AboutPresenter()
    let onNavigate: (AboutNavigationDestination) -> Void
    let onEnableComposeMultiplatform: () -> Void

    @State private var showSwitchToComposeMultiplatformAlert = false

    var version: String {
        Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String ?? ""
    }

    public init(
        onNavigate: @escaping (AboutNavigationDestination) -> Void = { _ in },
        onEnableComposeMultiplatform: @escaping () -> Void = {}
    ) {
        self.onNavigate = onNavigate
        self.onEnableComposeMultiplatform = onEnableComposeMultiplatform
    }

    public var body: some View {
        ScrollView {
            VStack(spacing: 32) {
                KeyVisual()
                    .padding(.top, 28)

                VStack(spacing: 32) {
                    creditsSection

                    othersSection

                    socialMediaSection

                    versionSection
                }
                .padding(.horizontal, 16)
            }
            .padding(.bottom, 80)  // Tab bar padding
        }
        .background(AssetColors.surface.swiftUIColor)
        .navigationTitle("About")
        #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
        #endif
    }

    @ViewBuilder
    private var creditsSection: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Credits")
                .foregroundStyle(AssetColors.primaryFixed.swiftUIColor)
                .font(.subheadline)
                .padding(.bottom, 8)

            VStack(spacing: 0) {
                AboutButton(
                    title: "Contributors",
                    imageName: "ic_diversity"
                ) {
                    presenter.contributorsTapped()
                    onNavigate(.contributors)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)

                AboutButton(
                    title: "Staffs",
                    imageName: "ic_sentiment_very_satisfied"
                ) {
                    presenter.staffsTapped()
                    onNavigate(.staff)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)

                AboutButton(
                    title: "Sponsors",
                    imageName: "ic_apartment"
                ) {
                    presenter.sponsorsTapped()
                    onNavigate(.sponsors)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)
            }
        }
    }

    @ViewBuilder
    private var othersSection: some View {
        // swiftlint:disable:next closure_body_length
        VStack(alignment: .leading, spacing: 0) {
            Text("Others")
                .foregroundStyle(AssetColors.primaryFixed.swiftUIColor)
                .font(.subheadline)
                .padding(.bottom, 8)

            VStack(spacing: 0) {
                AboutButton(
                    title: "Code of Conduct",
                    imageName: "ic_gavel"
                ) {
                    presenter.codeOfConductTapped()
                    onNavigate(.codeOfConduct)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)

                AboutButton(
                    title: "Licenses",
                    imageName: "ic_file_copy"
                ) {
                    presenter.licensesTapped()
                    onNavigate(.licenses)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)

                AboutButton(
                    title: "Privacy Policy",
                    imageName: "ic_privacy_tip"
                ) {
                    presenter.privacyPolicyTapped()
                    onNavigate(.privacyPolicy)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)

                AboutButton(
                    title: "Settings",
                    imageName: "ic_settings"
                ) {
                    presenter.settingsTapped()
                    onNavigate(.settings)
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)

                AboutButton(
                    title: "Switch to Compose Multiplatform",
                    imageName: "switch.2",
                    isSystemImage: true
                ) {
                    showSwitchToComposeMultiplatformAlert = true
                    presenter.switchToComposeMultiplatformTapped()
                }
                .alert("Switch UI", isPresented: $showSwitchToComposeMultiplatformAlert) {
                    Button("Cancel", role: .cancel) {}
                    Button("Switch") {
                        onEnableComposeMultiplatform()
                    }
                } message: {
                    Text("Switch UI from SwiftUI to Compose Multiplatform. Are you sure you want to do this?")
                }

                Divider()
                    .background(AssetColors.outlineVariant.swiftUIColor)
            }
        }
    }

    @ViewBuilder
    private var socialMediaSection: some View {
        HStack(spacing: 12) {
            SocialButton(
                imageName: "ic_youtube_logo"
            ) {
                presenter.youtubeTapped()
                // TODO: Open in Safari when implemented
            }

            SocialButton(
                imageName: "ic_xcom_logo"
            ) {
                presenter.xcomTapped()
                // TODO: Open in Safari when implemented
            }

            SocialButton(
                imageName: "ic_medium_logo"
            ) {
                presenter.mediumTapped()
                // TODO: Open in Safari when implemented
            }
        }
    }

    @ViewBuilder
    private var versionSection: some View {
        VStack(spacing: 0) {
            Text("App Version")
                .font(.caption)

            Text(version)
                .font(.caption)
        }
        .foregroundStyle(AssetColors.primaryFixed.swiftUIColor)
        .padding(.bottom, 16)
    }
}

struct AboutButton: View {
    let title: String
    let imageName: String
    var isSystemImage: Bool = false
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            HStack(spacing: 12) {
                if isSystemImage {
                    Image(systemName: imageName)
                        .frame(width: 24, height: 24)
                        .foregroundColor(AssetColors.primaryFixed.swiftUIColor)
                } else {
                    Image(imageName, bundle: .module)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 24, height: 24)
                }

                Text(title)
                    .foregroundColor(AssetColors.primaryFixed.swiftUIColor)

                Spacer()
            }
            .padding(.vertical, 16)
            .padding(.horizontal, 12)
            .contentShape(Rectangle())
        }
        .buttonStyle(PlainButtonStyle())
    }
}

struct SocialButton: View {
    let imageName: String
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Image(imageName, bundle: .module)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 48, height: 48)
        }
    }
}

#Preview {
    AboutScreen()
}
