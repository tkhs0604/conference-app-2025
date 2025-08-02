import SwiftUI
import Model
import Extension
import Theme

public struct RoomTag: View {
    let room: Room
    
    public init(room: Room) {
        self.room = room
    }
    
    public var body: some View {
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

public struct LanguageTag: View {
    let language: TimetableLanguage
    
    public init(language: TimetableLanguage) {
        self.language = language
    }
    
    public var body: some View {
        Text(language.displayLanguage)
            .font(.system(size: 12, weight: .medium))
            .foregroundStyle(AssetColors.onSurfaceVariant)
            .padding(.horizontal, 8)
            .padding(.vertical, 4)
            .overlay(
                RoundedRectangle(cornerRadius: 4)
                    .stroke(AssetColors.outline, lineWidth: 1)
            )
    }
}

public struct CircularUserIcon: View {
    let imageUrl: String?
    
    public init(imageUrl: String?) {
        self.imageUrl = imageUrl
    }
    
    public var body: some View {
        if let imageUrl = imageUrl, let url = URL(string: imageUrl) {
            AsyncImage(url: url) { image in
                image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
            } placeholder: {
                Image(systemName: "person.circle.fill")
                    .foregroundStyle(AssetColors.outline)
            }
            .clipShape(Circle())
        } else {
            Image(systemName: "person.circle.fill")
                .resizable()
                .foregroundStyle(AssetColors.outline)
        }
    }
}