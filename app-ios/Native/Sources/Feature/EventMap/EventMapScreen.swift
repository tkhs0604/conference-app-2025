import SwiftUI
import Theme

public struct EventMapScreen: View {
    @State private var presenter = EventMapPresenter()
    @State private var selectedFloorMap: FloorMap? = .b1f
    
    public init() {}
    
    public var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0) {
                // Description
                Text("DroidKaigiでは、セッション以外にも参加者が楽しめるイベントを開催。コミュニケーションや技術交流を通じてカンファレンスを満喫しましょう！")
                    .font(.body)
                    .foregroundColor(AssetColors.onSurfaceVariant.swiftUIColor)
                    .padding(.horizontal, 16)
                    .padding(.vertical, 10)
                
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
                        .padding(.all, 16)
                }
                
                // Events section
                VStack(alignment: .leading, spacing: 0) {
                    ForEach(Array(presenter.events.enumerated()), id: \.0) { (index, event) in
                        EventItem(event: event) { url in
                            presenter.moreDetailButtonTapped(url)
                        }
                        if index != presenter.events.count - 1 {
                            Divider()
                                .padding(.horizontal, 16)
                                .foregroundStyle(AssetColors.outlineVariant.swiftUIColor)
                        }
                    }
                }
                .padding(.bottom, 80) // Tab bar padding
            }
        }
        .background(AssetColors.background.swiftUIColor)
        .navigationTitle("イベントマップ")
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
