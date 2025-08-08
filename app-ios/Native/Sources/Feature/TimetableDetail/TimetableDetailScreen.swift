import Component
import Model
import SwiftUI
import Theme

public struct TimetableDetailScreen: View {
    @State private var presenter: TimetableDetailPresenter
    @State private var showingURL: URL?
    @Environment(\.dismiss) private var dismiss

    public init(timetableItem: TimetableItemWithFavorite) {
        self._presenter = State(initialValue: TimetableDetailPresenter(timetableItem: timetableItem))
    }

    public var body: some View {
        GeometryReader { proxy in
            VStack(spacing: 0) {
                ScrollView {
                    VStack(spacing: 0) {
                        presenter.timetableItem.timetableItem.room.roomTheme.containerColor
                            .frame(height: proxy.safeAreaInsets.top)
                        headLine
                            .padding(.bottom, 24)
                    }
                    detail
                        .padding(.horizontal, 16)
                    targetAudience
                        .padding(16)
                    if presenter.timetableItem.timetableItem.asset.videoUrl != nil
                        || presenter.timetableItem.timetableItem.asset.slideUrl != nil
                    {
                        archive
                            .padding(16)
                    }
                }

                footer
            }
            .background(Color.white)
            .frame(maxWidth: .infinity)
            .ignoresSafeArea(edges: [.top])
        }
        .tint(presenter.timetableItem.timetableItem.room.roomTheme.primaryColor)
        .sheet(
            isPresented: .init(
                get: { showingURL != nil },
                set: { if !$0 { showingURL = nil } }
            )
        ) {
            if let url = showingURL {
                SafariView(url: url)
                    .ignoresSafeArea()
            }
        }
        .overlay(alignment: .bottom) {
            if presenter.showToast {
                ToastView(message: presenter.toastMessage)
                    .transition(.move(edge: .bottom).combined(with: .opacity))
                    .onAppear {
                        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                            withAnimation {
                                presenter.showToast = false
                            }
                        }
                    }
            }
        }
        .animation(.easeInOut, value: presenter.showToast)
    }

    @MainActor var footer: some View {
        HStack(spacing: 28) {
            if let url = presenter.shareSession() {
                ShareLink(item: url) {
                    Image(systemName: "square.and.arrow.up")
                        .resizable()
                        .frame(width: 24, height: 24)
                        .foregroundColor(.primary)
                        .accessibilityLabel("Share session")
                }
            }
            Button {
                presenter.addToCalendar()
            } label: {
                Image(systemName: "calendar.badge.plus")
                    .resizable()
                    .frame(width: 24, height: 24)
                    .foregroundColor(.primary)
                    .accessibilityLabel("Add to calendar")
            }
            Spacer()
            Button {
                presenter.toggleFavorite()
            } label: {
                Group {
                    if presenter.isFavorite {
                        Image(systemName: "heart.fill")
                    } else {
                        Image(systemName: "heart")
                    }
                }
                .accessibilityLabel(presenter.isFavorite ? "Remove from favorites" : "Add to favorites")
                .frame(width: 56, height: 56)
                .background(Color.gray.opacity(0.15))
                .clipShape(RoundedRectangle(cornerRadius: 16))
            }
            .sensoryFeedback(.impact, trigger: presenter.isFavorite) { _, newValue in newValue }
        }
        .frame(height: 80)
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 16)
        .background(Color.gray.opacity(0.1))
    }

    @MainActor var headLine: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(spacing: 4) {
                RoomTag(room: presenter.timetableItem.timetableItem.room)

                LanguageTag(language: presenter.timetableItem.timetableItem.language)
            }
            .padding(.bottom, 8)

            Text(presenter.timetableItem.timetableItem.title.currentLangTitle)
                .font(.system(size: 20, weight: .semibold))
                .foregroundStyle(.secondary)
                .padding(.bottom, 20)
                .frame(maxWidth: .infinity, alignment: .leading)

            ForEach(presenter.timetableItem.timetableItem.speakers, id: \.id) { speaker in
                HStack(spacing: 12) {
                    CircularUserIcon(imageUrl: speaker.imageUrl)
                        .frame(width: 52, height: 52)

                    VStack(alignment: .leading, spacing: 8) {
                        Text(speaker.name)
                            .font(.system(size: 16))
                            .foregroundStyle(.primary)
                        Text(speaker.tagLine)
                            .font(.system(size: 12))
                            .foregroundStyle(.primary)
                    }

                    Spacer()
                }
            }
            .padding(.bottom, 20)
        }
        .padding([.top, .horizontal], 16)
        .background(presenter.timetableItem.timetableItem.room.roomTheme.containerColor)
    }

    @MainActor var detail: some View {
        VStack(alignment: .leading, spacing: 20) {
            VStack(spacing: 16) {
                InformationRow(
                    icon: Image(systemName: "clock"),
                    title: "日時",
                    titleColor: presenter.timetableItem.timetableItem.room.roomTheme.primaryColor,
                    content: formattedDateTimeString
                )
                InformationRow(
                    icon: Image(systemName: "location"),
                    title: "場所",
                    titleColor: presenter.timetableItem.timetableItem.room.roomTheme.primaryColor,
                    content: presenter.timetableItem.timetableItem.room.name.currentLangTitle
                )
                InformationRow(
                    icon: Image(systemName: "globe"),
                    title: "対応言語",
                    titleColor: presenter.timetableItem.timetableItem.room.roomTheme.primaryColor,
                    content: getSupportedLangString()
                )
                InformationRow(
                    icon: Image(systemName: "tag"),
                    title: "カテゴリ",
                    titleColor: presenter.timetableItem.timetableItem.room.roomTheme.primaryColor,
                    content: presenter.timetableItem.timetableItem.category.title.currentLangTitle
                )
            }
            .padding(16)
            .overlay(
                presenter.timetableItem.timetableItem.room.roomTheme.primaryColor,
                in: RoundedRectangle(cornerRadius: 4)
                    .stroke(style: .init(lineWidth: 1, dash: [2, 2]))
            )

            if let session = presenter.timetableItem.timetableItem as? TimetableItemSession {
                SessionDescriptionView(
                    content: session.description.currentLangTitle,
                    themeColor: session.room.roomTheme.primaryColor
                )
                .padding(.bottom, 24)
            }
        }
    }

    @MainActor var targetAudience: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("想定参加者")
                .font(.system(size: 22, weight: .bold))
                .foregroundStyle(presenter.timetableItem.timetableItem.room.roomTheme.primaryColor)

            Text(presenter.timetableItem.timetableItem.targetAudience)
                .font(.system(size: 16))
                .foregroundStyle(.secondary)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    @MainActor var archive: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text("アーカイブ")
                .font(.system(size: 22, weight: .bold))
                .foregroundStyle(presenter.timetableItem.timetableItem.room.roomTheme.primaryColor)

            HStack {
                if let slideUrlString = presenter.timetableItem.timetableItem.asset.slideUrl,
                    let slideUrl = URL(string: slideUrlString)
                {
                    Button {
                        showingURL = slideUrl
                    } label: {
                        VStack {
                            Label(
                                title: {
                                    Text("スライド")
                                        .font(.system(size: 14, weight: .medium))
                                        .foregroundStyle(Color.white)
                                },
                                icon: { Image(systemName: "doc") }
                            )
                        }
                        .frame(height: 40)
                        .frame(maxWidth: .infinity)
                        .background(presenter.timetableItem.timetableItem.room.roomTheme.primaryColor)
                        .clipShape(Capsule())
                    }
                }
                if let videoUrlString = presenter.timetableItem.timetableItem.asset.videoUrl,
                    let videoUrl = URL(string: videoUrlString)
                {
                    Button {
                        showingURL = videoUrl
                    } label: {
                        VStack {
                            Label(
                                title: {
                                    Text("録画")
                                        .font(.system(size: 14, weight: .medium))
                                        .foregroundStyle(Color.white)
                                },
                                icon: { Image(systemName: "play") }
                            )
                        }
                        .frame(height: 40)
                        .frame(maxWidth: .infinity)
                        .background(presenter.timetableItem.timetableItem.room.roomTheme.primaryColor)
                        .clipShape(Capsule())
                    }
                }
            }
        }
    }

    private var formattedDateTimeString: String {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy.MM.dd"
        let date = presenter.timetableItem.timetableItem.startsAt
        let dateString = formatter.string(from: date)

        formatter.dateFormat = "HH:mm"
        let startTime = formatter.string(from: date)
        let endDate = presenter.timetableItem.timetableItem.endsAt
        let endTime = formatter.string(from: endDate)

        return "\(dateString) / \(startTime) ~ \(endTime)"
    }

    private func getSupportedLangString() -> String {
        _ = Locale.current.language.languageCode == .japanese
        return presenter.timetableItem.timetableItem.language.langOfSpeaker
    }
}

struct ToastView: View {
    let message: String

    var body: some View {
        Text(message)
            .font(.system(size: 14))
            .padding(.horizontal, 16)
            .padding(.vertical, 10)
            .background(Color.black)
            .foregroundColor(.white)
            .cornerRadius(20)
            .padding(.bottom, 100)
    }
}

#Preview {
    TimetableDetailScreen(
        timetableItem: TimetableItemWithFavorite(
            timetableItem: TimetableItemSession(
                id: TimetableItemId(value: "1"),
                title: MultiLangText(jaTitle: "サンプルセッション", enTitle: "Sample Session"),
                startsAt: Date(),
                endsAt: Date().addingTimeInterval(3_600),
                category: TimetableCategory(id: 1, title: MultiLangText(jaTitle: "開発", enTitle: "Development")),
                sessionType: .regular,
                room: Room(id: 1, name: MultiLangText(jaTitle: "ルームA", enTitle: "Room A"), type: .roomF, sort: 1),
                targetAudience: "初心者向け",
                language: TimetableLanguage(langOfSpeaker: "JA", isInterpretationTarget: false),
                asset: TimetableAsset(videoUrl: nil, slideUrl: nil),
                levels: ["初級"],
                speakers: [],
                description: MultiLangText(jaTitle: "セッションの説明", enTitle: "Session description")
            ),
            isFavorited: false
        )
    )
}
