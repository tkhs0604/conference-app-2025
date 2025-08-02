import Model
import SwiftUI
import Theme
import Presentation

struct TimetableCard: View {
    let timetableItem: any TimetableItem
    let isFavorite: Bool
    let onTap: (any TimetableItem) -> Void
    let onTapFavorite: (any TimetableItem, CGPoint?) -> Void

    @State private var dragLocation: CGPoint?
    
    var body: some View {
        Button(action: {
            onTap(timetableItem)
        }) {
            VStack(alignment: .leading, spacing: 8) {
                headerRow
                
                Text(timetableItem.title.currentLangTitle)
                    .font(.system(size: 16, weight: .medium))
                    .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                    .multilineTextAlignment(.leading)
                    .fixedSize(horizontal: false, vertical: true)
                
                if !timetableItem.speakers.isEmpty {
                    speakersList
                }
            }
            .padding(12)
            .frame(maxWidth: .infinity, alignment: .leading)
            .overlay(
                RoundedRectangle(cornerRadius: 4)
                    .stroke(AssetColors.outlineVariant.swiftUIColor, lineWidth: 1)
            )
            .cornerRadius(4)
        }
        .buttonStyle(PlainButtonStyle())
    }
    
    private var headerRow: some View {
        HStack(spacing: 8) {
            RoomTag(room: timetableItem.room)
            
            LanguageTag(language: timetableItem.language)
            
            Spacer()
            
            favoriteButton
        }
    }
    
    private var favoriteButton: some View {
        Button(action: {
            let location = dragLocation
            onTapFavorite(timetableItem, location)
        }) {
            Image(systemName: isFavorite ? "heart.fill" : "heart")
                .foregroundStyle(
                    isFavorite
                        ? AssetColors.primaryFixed.swiftUIColor
                        : AssetColors.onSurfaceVariant.swiftUIColor
                )
                .frame(width: 24, height: 24)
        }
        .buttonStyle(PlainButtonStyle())
        .background(
            GeometryReader { geometry in
                Color.clear
                    .onContinuousHover { phase in
                        switch phase {
                        case .active(let location):
                            dragLocation = location
                        case .ended:
                            break
                        }
                    }
            }
        )
    }
    
    private var speakersList: some View {
        VStack(alignment: .leading, spacing: 4) {
            ForEach(timetableItem.speakers) { speaker in
                HStack(spacing: 8) {
                    CircularUserIcon(imageUrl: speaker.imageUrl)
                        .frame(width: 32, height: 32)
                    
                    Text(speaker.name)
                        .font(.system(size: 14, weight: .medium))
                        .foregroundStyle(AssetColors.onSurface.swiftUIColor)
                }
            }
        }
    }
}

struct RoomTag: View {
    let room: Room
    
    var body: some View {
        Text(room.displayName)
            .font(.system(size: 12, weight: .medium))
            .foregroundStyle(room.color)
            .padding(.horizontal, 8)
            .padding(.vertical, 4)
            .overlay(
                RoundedRectangle(cornerRadius: 4)
                    .stroke(room.color, lineWidth: 1)
            )
    }
}

struct LanguageTag: View {
    let language: TimetableLanguage
    
    var body: some View {
        Text(language.displayLanguage)
            .font(.system(size: 12, weight: .medium))
            .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
            .padding(.horizontal, 8)
            .padding(.vertical, 4)
            .overlay(
                RoundedRectangle(cornerRadius: 4)
                    .stroke(AssetColors.outline.swiftUIColor, lineWidth: 1)
            )
    }
}

struct CircularUserIcon: View {
    let imageUrl: String?
    
    var body: some View {
        if let imageUrl = imageUrl, let url = URL(string: imageUrl) {
            AsyncImage(url: url) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
            } placeholder: {
                Image(systemName: "person.circle.fill")
                    .foregroundStyle(AssetColors.outline.swiftUIColor)
            }
            .clipShape(Circle())
        } else {
            Image(systemName: "person.circle.fill")
                .resizable()
                .foregroundStyle(AssetColors.outline.swiftUIColor)
        }
    }
}

// TODO: Add preview with proper test data
//#Preview {
//    TimetableCard(
//        timetableItem: ...,
//        isFavorite: false,
//        onTap: { _ in },
//        onTapFavorite: { _, _ in }
//    )
//    .padding()
//}
