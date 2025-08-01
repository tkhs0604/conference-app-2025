import SwiftUI
import Theme
import Model
import Component

public struct SearchScreen: View {
    @State private var presenter = SearchPresenter()
    @FocusState private var isSearchFieldFocused: Bool
    let onNavigate: (SearchNavigationDestination) -> Void
    
    public init(onNavigate: @escaping (SearchNavigationDestination) -> Void = { _ in }) {
        self.onNavigate = onNavigate
    }
    
    public var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                // Search bar
                HStack {
                    Image(systemName: "magnifyingglass")
                        .foregroundColor(.secondary)
                    
                    TextField("Search sessions...", text: $presenter.searchWord)
                        .textFieldStyle(PlainTextFieldStyle())
                        .focused($isSearchFieldFocused)
                    
                    if !presenter.searchWord.isEmpty {
                        Button(action: {
                            presenter.searchWord = ""
                        }) {
                            Image(systemName: "xmark.circle.fill")
                                .foregroundColor(.secondary)
                        }
                    }
                }
                .padding(12)
                .background(Color.primary.opacity(0.1))
                .cornerRadius(10)
                .padding(.horizontal, 16)
                .padding(.vertical, 8)
                
                // Filters
                ScrollView(.vertical, showsIndicators: false) {
                    VStack(spacing: 16) {
                        SearchFilterSection(
                            title: "Day",
                            selection: $presenter.selectedDay
                        )
                        
                        categorySection
                        
                        SearchFilterSection(
                            title: "Session Type",
                            selection: $presenter.selectedSessionType
                        )
                        
                        languageSection
                    }
                    .padding(.vertical, 8)
                }
                .frame(height: 200)
                
                Divider()
                
                // Results
                if presenter.filteredTimetableItems.isEmpty {
                    emptyView
                } else {
                    ScrollView {
                        LazyVStack(spacing: 16) {
                            ForEach(presenter.filteredTimetableItems, id: \.timetableItem.id) { item in
                                TimetableCard(
                                    timetableItem: item.timetableItem,
                                    isFavorite: presenter.timetable.favoriteIds.contains(item.timetableItem.id.value),
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
                        .padding(.bottom, 80) // Tab bar padding
                    }
                }
            }
            .background(Color.primary.opacity(0.02))
            .navigationTitle("Search")
            #if os(iOS)
            .navigationBarTitleDisplayMode(.large)
            #endif
            .task {
                await presenter.loadInitial()
                isSearchFieldFocused = true
            }
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
                    .foregroundColor(.secondary)
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
                        
                        ForEach(categories, id: \.id) { category in
                            SearchFilterChip<TimetableCategory>(
                                title: category.title.currentLangTitle,
                                isSelected: presenter.selectedCategory?.id == category.id,
                                onTap: {
                                    presenter.selectedCategory = presenter.selectedCategory?.id == category.id ? nil : category
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
                    SearchFilterChip<String>(
                        title: "All",
                        isSelected: presenter.selectedLanguage == nil,
                        onTap: {
                            presenter.selectedLanguage = nil
                        }
                    )
                    
                    // Common languages - you can expand this based on actual data
                    ForEach(["Japanese", "English"], id: \.self) { language in
                        SearchFilterChip<String>(
                            title: language,
                            isSelected: presenter.selectedLanguage?.langOfSpeaker == language,
                            onTap: {
                                if presenter.selectedLanguage?.langOfSpeaker == language {
                                    presenter.selectedLanguage = nil
                                } else {
                                    presenter.selectedLanguage = TimetableLanguage(
                                        langOfSpeaker: language,
                                        isInterpretationTarget: false
                                    )
                                }
                            }
                        )
                    }
                }
                .padding(.horizontal, 16)
            }
        }
    }
    
    @ViewBuilder
    private var emptyView: some View {
        VStack(spacing: 24) {
            // TODO: Replace with actual empty state illustration
            Image(systemName: "magnifyingglass")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 80, height: 80)
                .foregroundColor(.secondary.opacity(0.5))
            
            VStack(spacing: 8) {
                if !presenter.searchWord.isEmpty {
                    Text("No results for \"\(presenter.searchWord)\"")
                        .font(.title3)
                        .fontWeight(.semibold)
                    
                    Text("Try adjusting your search or filters")
                        .font(.body)
                        .foregroundColor(.secondary)
                } else {
                    Text("Search for sessions")
                        .font(.title3)
                        .fontWeight(.semibold)
                    
                    Text("Enter keywords or use filters to find sessions")
                        .font(.body)
                        .foregroundColor(.secondary)
                }
            }
            .multilineTextAlignment(.center)
            .padding(.horizontal, 40)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

#Preview {
    SearchScreen()
}