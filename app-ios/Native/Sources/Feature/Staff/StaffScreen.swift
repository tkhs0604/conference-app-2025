import SwiftUI
import Theme

public struct StaffScreen: View {
    @State private var presenter = StaffPresenter()
    
    public init() {}
    
    public var body: some View {
        NavigationStack {
            Group {
                if presenter.isLoading {
                    ProgressView()
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                } else {
                    ScrollView {
                        LazyVStack(spacing: 0) {
                            ForEach(presenter.staffList) { staff in
                                VStack(spacing: 0) {
                                    StaffLabel(staff: staff)
                                        .padding(.horizontal, 16)
                                        .onTapGesture {
                                            presenter.staffTapped(staff)
                                            // TODO: Open GitHub profile
                                        }
                                    
                                    Divider()
                                        .padding(.leading, 88)
                                }
                            }
                        }
                        .padding(.bottom, 80) // Tab bar padding
                    }
                }
            }
            .background(Color.primary.opacity(0.02))
            .navigationTitle("Staff")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
            #endif
            .task {
                await presenter.loadStaff()
            }
        }
    }
}

#Preview {
    StaffScreen()
}