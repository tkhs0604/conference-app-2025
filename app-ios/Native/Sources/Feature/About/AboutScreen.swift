import SwiftUI
import Theme
import ContributorFeature
import SponsorFeature
import StaffFeature

public struct AboutScreen: View {
    @State private var presenter = AboutPresenter()
    @State private var selectedTab: AboutTab?
    
    private enum AboutTab: String, CaseIterable {
        case contributors = "Contributors"
        case staffs = "Staffs"
        case sponsors = "Sponsors"
    }
    
    var version: String {
        Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String ?? ""
    }
    
    public init() {}
    
    public var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 32) {
                    KeyVisual()
                        .padding(.top, 28)
                    
                    creditsSection
                    
                    othersSection
                    
                    socialMediaSection
                    
                    versionSection
                }
                .padding(.horizontal, 16)
                .padding(.bottom, 80) // Tab bar padding
            }
            .background(Color.primary.opacity(0.05))
            .navigationTitle("About")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
            #endif
            .navigationDestination(item: $selectedTab) { tab in
                switch tab {
                case .contributors:
                    ContributorScreen()
                case .staffs:
                    StaffScreen()
                case .sponsors:
                    SponsorScreen()
                }
            }
        }
    }
    
    @ViewBuilder
    private var creditsSection: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Credits")
                .foregroundStyle(.secondary)
                .font(.subheadline)
                .padding(.bottom, 8)
            
            VStack(spacing: 0) {
                AboutButton(
                    title: "Contributors",
                    // TODO: Replace with actual diversity icon asset when available
                    systemImage: "person.3.fill"
                ) {
                    presenter.contributorsTapped()
                    selectedTab = .contributors
                }
                
                Divider()
                
                AboutButton(
                    title: "Staffs",
                    // TODO: Replace with actual satisfied icon asset when available
                    systemImage: "face.smiling.fill"
                ) {
                    presenter.staffsTapped()
                    selectedTab = .staffs
                }
                
                Divider()
                
                AboutButton(
                    title: "Sponsors",
                    // TODO: Replace with actual apartment icon asset when available
                    systemImage: "building.2.fill"
                ) {
                    presenter.sponsorsTapped()
                    selectedTab = .sponsors
                }
            }
        }
    }
    
    @ViewBuilder
    private var othersSection: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Others")
                .foregroundStyle(.secondary)
                .font(.subheadline)
                .padding(.bottom, 8)
            
            VStack(spacing: 0) {
                AboutButton(
                    title: "Code of Conduct",
                    // TODO: Replace with actual gavel icon asset when available
                    systemImage: "gavel.fill"
                ) {
                    presenter.codeOfConductTapped()
                    // TODO: Open in Safari when implemented
                }
                
                Divider()
                
                AboutButton(
                    title: "Privacy Policy",
                    // TODO: Replace with actual privacy tip icon asset when available
                    systemImage: "lock.shield.fill"
                ) {
                    presenter.privacyPolicyTapped()
                    // TODO: Open in Safari when implemented
                }
            }
        }
    }
    
    @ViewBuilder
    private var socialMediaSection: some View {
        HStack(spacing: 12) {
            SocialButton(
                // TODO: Replace with actual YouTube social circle asset when available
                systemImage: "play.circle.fill",
                color: .red
            ) {
                presenter.youtubeTapped()
                // TODO: Open in Safari when implemented
            }
            
            SocialButton(
                // TODO: Replace with actual X social circle asset when available
                systemImage: "xmark.circle.fill",
                color: .black
            ) {
                presenter.xcomTapped()
                // TODO: Open in Safari when implemented
            }
            
            SocialButton(
                // TODO: Replace with actual Medium social circle asset when available
                systemImage: "book.circle.fill",
                color: .gray
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
        .foregroundStyle(.secondary)
        .padding(.bottom, 16)
    }
}

struct AboutButton: View {
    let title: String
    let systemImage: String
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            HStack(spacing: 12) {
                Image(systemName: systemImage)
                    .frame(width: 24, height: 24)
                    .foregroundColor(.accentColor)
                
                Text(title)
                    .foregroundColor(.primary)
                
                Spacer()
                
                Image(systemName: "chevron.right")
                    .foregroundColor(.secondary)
                    .font(.caption)
            }
            .padding(.vertical, 16)
            .padding(.horizontal, 12)
            .contentShape(Rectangle())
        }
        .buttonStyle(PlainButtonStyle())
    }
}

struct SocialButton: View {
    let systemImage: String
    let color: Color
    let action: () -> Void
    
    var body: some View {
        Button(action: action) {
            Image(systemName: systemImage)
                .resizable()
                .frame(width: 48, height: 48)
                .foregroundColor(color)
        }
    }
}

#Preview {
    AboutScreen()
}