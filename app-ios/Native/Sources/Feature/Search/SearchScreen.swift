import Component
import Model
import SwiftUI
import Theme

public struct SearchScreen: View {
    @State private var presenter = SearchPresenter()
    @FocusState private var isSearchFieldFocused: Bool
    let onNavigate: (SearchNavigationDestination) -> Void

    public init(onNavigate: @escaping (SearchNavigationDestination) -> Void = { _ in }) {
        self.onNavigate = onNavigate
    }

    public var body: some View {
        VStack(spacing: 0) {
            // Search bar
            HStack {
                Image(systemName: "magnifyingglass")
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)

                TextField("Search sessions", text: $presenter.searchWord)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .focused($isSearchFieldFocused)
                    .submitLabel(.search)
                    .onSubmit {
                        // Search is automatic with binding
                    }

                if !presenter.searchWord.isEmpty {
                    Button(action: {
                        presenter.searchWord = ""
                    }) {
                        Image(systemName: "xmark.circle.fill")
                            .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                    }
                }
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(AssetColors.surfaceVariant.swiftUIColor)

            // Filters
            ScrollView {
                VStack(alignment: .leading, spacing: 16) {
                    // Category filter
                    categorySection

                    // Language filter
                    languageSection

                    // Day filter (if applicable)
                    daySection

                    // Room filter (if applicable)
                    roomSection

                    // Search results
                    if !presenter.searchWord.isEmpty || presenter.selectedDay != nil
                        || presenter.selectedCategory != nil || presenter.selectedLanguage != nil
                    {
                        Divider()
                            .padding(.vertical, 8)

                        Text("Results")
                            .font(.headline)
                            .padding(.horizontal, 16)

                        LazyVStack(spacing: 12) {
                            ForEach(presenter.filteredTimetableItems) { item in
                                TimetableCard(
                                    timetableItem: item.timetableItem,
                                    isFavorite: item.isFavorited,
                                    onTap: { _ in
                                        onNavigate(.timetableDetail(item))
                                    },
                                    onTapFavorite: { _, _ in
                                        presenter.toggleFavorite(item.timetableItem.id)
                                    }
                                )
                            }
                        }
                        .padding(.horizontal, 16)
                        .padding(.vertical, 16)
                        .padding(.bottom, 80)  // Tab bar padding
                    }
                }
            }
        }
        .background(AssetColors.surface.swiftUIColor)
        .navigationTitle("Search")
        #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
        #endif
        .onAppear {
            presenter.loadInitial()
            isSearchFieldFocused = true
        }
    }

    @ViewBuilder
    private var categorySection: some View {
        if let timetable = presenter.timetable.timetable {
            let uniqueCategories = Set(timetable.timetableItems.map { $0.category })
            let categories = Array(uniqueCategories).sorted { $0.id < $1.id }

            VStack(alignment: .leading, spacing: 8) {
                Text("Category")
                    .font(.caption)
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                    .padding(.horizontal, 16)

                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 8) {
                        SearchFilterChip<TimetableCategory>(
                            title: "All",
                            isSelected: presenter.selectedCategory == nil,
                            onTap: {
                                presenter.selectedCategory = nil
                            }
                        )

                        ForEach(categories) { category in
                            SearchFilterChip<TimetableCategory>(
                                title: category.title.jaTitle,
                                isSelected: presenter.selectedCategory == category,
                                onTap: {
                                    presenter.selectedCategory = presenter.selectedCategory == category ? nil : category
                                }
                            )
                        }
                    }
                    .padding(.horizontal, 16)
                }
            }
        }
    }

    @ViewBuilder
    private var languageSection: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Language")
                .font(.caption)
                .foregroundColor(.secondary)
                .padding(.horizontal, 16)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 8) {
                    SearchFilterChip<TimetableLanguage>(
                        title: "All",
                        isSelected: presenter.selectedLanguage == nil,
                        onTap: {
                            presenter.selectedLanguage = nil
                        }
                    )

                    SearchFilterChip<TimetableLanguage>(
                        title: "Japanese",
                        isSelected: presenter.selectedLanguage?.langOfSpeaker == "JA",
                        onTap: {
                            if presenter.selectedLanguage?.langOfSpeaker == "JA" {
                                presenter.selectedLanguage = nil
                            } else {
                                presenter.selectedLanguage = TimetableLanguage(
                                    langOfSpeaker: "JA", isInterpretationTarget: false)
                            }
                        }
                    )

                    SearchFilterChip<TimetableLanguage>(
                        title: "English",
                        isSelected: presenter.selectedLanguage?.langOfSpeaker == "EN",
                        onTap: {
                            if presenter.selectedLanguage?.langOfSpeaker == "EN" {
                                presenter.selectedLanguage = nil
                            } else {
                                presenter.selectedLanguage = TimetableLanguage(
                                    langOfSpeaker: "EN", isInterpretationTarget: false)
                            }
                        }
                    )

                    SearchFilterChip<TimetableLanguage>(
                        title: "Mixed",
                        isSelected: presenter.selectedLanguage?.langOfSpeaker == "MIXED",
                        onTap: {
                            if presenter.selectedLanguage?.langOfSpeaker == "MIXED" {
                                presenter.selectedLanguage = nil
                            } else {
                                presenter.selectedLanguage = TimetableLanguage(
                                    langOfSpeaker: "MIXED", isInterpretationTarget: false)
                            }
                        }
                    )
                }
                .padding(.horizontal, 16)
            }
        }
    }

    @ViewBuilder
    private var daySection: some View {
        if let _ = presenter.timetable.timetable {
            VStack(alignment: .leading, spacing: 8) {
                Text("Day")
                    .font(.caption)
                    .foregroundStyle(AssetColors.onSurfaceVariant.swiftUIColor)
                    .padding(.horizontal, 16)

                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 8) {
                        SearchFilterChip<DroidKaigi2024Day>(
                            title: "All",
                            isSelected: presenter.selectedDay == nil,
                            onTap: {
                                presenter.selectedDay = nil
                            }
                        )

                        ForEach([DroidKaigi2024Day.conferenceDay1, .conferenceDay2], id: \.self) { day in
                            SearchFilterChip<DroidKaigi2024Day>(
                                title: day == .conferenceDay1 ? "Day 1" : "Day 2",
                                isSelected: presenter.selectedDay == day,
                                onTap: {
                                    presenter.selectedDay = presenter.selectedDay == day ? nil : day
                                }
                            )
                        }
                    }
                    .padding(.horizontal, 16)
                }
            }
        }
    }

    @ViewBuilder
    private var roomSection: some View {
        EmptyView()  // TODO: Implement room filter when room data is available
    }
}

#Preview {
    SearchScreen()
}
