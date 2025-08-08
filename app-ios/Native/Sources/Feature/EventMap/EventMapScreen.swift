import SwiftUI
import Theme

public struct EventMapScreen: View {
    @State private var presenter = EventMapPresenter()
    @State private var selectedFloorMap: FloorMap? = .b1f
    
    public init() {}
    
    public var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                // Description
                Text("Navigate the venue with our interactive floor maps")
                    .font(.body)
                    .foregroundColor(.secondary)
                    .padding(.horizontal, 16)
                    .padding(.top, 8)
                
                // Floor selector
                FloorMapSelector(selected: $selectedFloorMap)
                    .onChange(of: selectedFloorMap) { _, newValue in
                        if let floor = newValue {
                            presenter.selectFloorMap(floor)
                        }
                    }
                
                // Map image
                if selectedFloorMap != nil {
                    // TODO: Replace with actual floor map images
                    Image(systemName: presenter.selectedFloorMap.image)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(maxWidth: .infinity)
                        .frame(height: 300)
                        .foregroundColor(.secondary)
                        .padding(.horizontal, 16)
                        .background(Color.primary.opacity(0.05))
                        .cornerRadius(12)
                        .padding(.horizontal, 16)
                }
                
                // Events section
                VStack(alignment: .leading, spacing: 16) {
                    Text("Events")
                        .font(.title2)
                        .fontWeight(.bold)
                        .padding(.horizontal, 16)
                    
                    ForEach(presenter.events) { event in
                        EventItem(event: event) { url in
                            presenter.moreDetailButtonTapped(url)
                        }
                    }
                }
                .padding(.bottom, 80) // Tab bar padding
            }
        }
        .background(Color.primary.opacity(0.02))
        .navigationTitle("Event Map")
        #if os(iOS)
        .navigationBarTitleDisplayMode(.large)
        #endif
        .onAppear {
            presenter.loadInitial()
            if selectedFloorMap == nil {
                selectedFloorMap = .b1f
            }
        }
    }
}

#Preview {
    EventMapScreen()
}