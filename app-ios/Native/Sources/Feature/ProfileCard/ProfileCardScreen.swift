import SwiftUI
import Theme

public struct ProfileCardScreen: View {
    @State private var presenter = ProfileCardPresenter()
    
    public init() {}
    
    public var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 24) {
                    // Profile Card
                    VStack(spacing: 20) {
                        // Avatar
                        Image(systemName: "person.circle.fill")
                            .resizable()
                            .frame(width: 120, height: 120)
                            .foregroundColor(.accentColor)
                        
                        // Name and Role
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
                        
                        // Bio
                        Text(presenter.userBio)
                            .font(.body)
                            .multilineTextAlignment(.center)
                            .padding(.horizontal, 20)
                        
                        // QR Code placeholder
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
                    .padding(24)
                    .background(Color.primary.opacity(0.05))
                    .cornerRadius(20)
                    .shadow(color: .black.opacity(0.1), radius: 10, x: 0, y: 5)
                    .padding(.horizontal, 16)
                    
                    // Action Buttons
                    VStack(spacing: 12) {
                        Button(action: {
                            presenter.shareProfileCard()
                        }) {
                            Label("Share Profile Card", systemImage: "square.and.arrow.up")
                                .frame(maxWidth: .infinity)
                                .padding()
                                .background(Color.accentColor)
                                .foregroundColor(.white)
                                .cornerRadius(12)
                        }
                        
                        Button(action: {
                            presenter.editProfile()
                        }) {
                            Label("Edit Profile", systemImage: "pencil")
                                .frame(maxWidth: .infinity)
                                .padding()
                                .background(Color.primary.opacity(0.1))
                                .foregroundColor(.primary)
                                .cornerRadius(12)
                        }
                    }
                    .padding(.horizontal, 16)
                }
                .padding(.vertical, 24)
                .padding(.bottom, 80) // Tab bar padding
            }
            .background(Color.primary.opacity(0.02))
            .navigationTitle("Profile Card")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
            #endif
        }
    }
}

#Preview {
    ProfileCardScreen()
}