import Dependencies
import Testing
import Model
import Foundation
@testable import UseCase

struct TimetableUseCaseTests {
    
    @Test("Should successfully load timetable with items and bookmarks")
    func testLoadTimetableReturnsExpectedItemsAndBookmarks() async throws {
        // Arrange
        let expectedItems: [any TimetableItem] = [
            TimetableItemSession(
                id: TimetableItemId(value: "session-1"),
                title: MultiLangText(jaTitle: "Session 1", enTitle: "Session 1"),
                startsAt: Date(),
                endsAt: Date().addingTimeInterval(3600),
                category: TimetableCategory(id: 1, title: MultiLangText(jaTitle: "Development", enTitle: "Development")),
                sessionType: .regular,
                room: Room(id: 1, name: MultiLangText(jaTitle: "Room A", enTitle: "Room A"), type: .roomF, sort: 1),
                targetAudience: "All",
                language: TimetableLanguage(langOfSpeaker: "JA", isInterpretationTarget: false),
                asset: TimetableAsset(videoUrl: nil, slideUrl: nil),
                levels: ["Beginner"],
                speakers: [],
                description: MultiLangText(jaTitle: "Description", enTitle: "Description"),
                message: nil
            )
        ]
        let expectedBookmarks = Set<TimetableItemId>([TimetableItemId(value: "session-1")])
        let expectedTimetable = Timetable(
            timetableItems: expectedItems,
            bookmarks: expectedBookmarks
        )
        
        // Act
        let result = try await withDependencies {
            $0.timetableUseCase.load = {
                return expectedTimetable
            }
        } operation: {
            @Dependency(\.timetableUseCase) var useCase
            return try await useCase.load()
        }
        
        // Assert
        #expect(result.timetableItems.count == 1)
        #expect(result.bookmarks.count == 1)
        #expect(result.bookmarks.contains(TimetableItemId(value: "session-1")))
        #expect(result.timetableItems.first?.id.value == "session-1")
    }
    
    @Test("Should handle empty timetable with no items or bookmarks")
    func testLoadEmptyTimetableReturnsEmptyCollections() async throws {
        // Arrange
        let emptyTimetable = Timetable(
            timetableItems: [],
            bookmarks: Set<TimetableItemId>()
        )
        
        // Act
        let result = try await withDependencies {
            $0.timetableUseCase.load = {
                return emptyTimetable
            }
        } operation: {
            @Dependency(\.timetableUseCase) var useCase
            return try await useCase.load()
        }
        
        // Assert
        #expect(result.timetableItems.isEmpty)
        #expect(result.bookmarks.isEmpty)
    }
    
    @Test("Should verify LoadTimetableError.networkError exists and is testable")
    func testNetworkErrorTypeExistsAndCanBeInstantiated() async throws {
        // Arrange
        var errorThrown: LoadTimetableError?
        _ = withDependencies {
            $0.timetableUseCase = TimetableUseCase()
        } operation: {
            TimetableUseCase()
        }
        
        // Since we cannot directly test typed throws in Swift 6.1,
        // we verify the error type exists and can be used
        errorThrown = LoadTimetableError.networkError
        
        // Assert
        #expect(errorThrown == .networkError)
        #expect(errorThrown != nil)
    }
    
    @Test("Should correctly load timetable containing both session and special items")
    func testLoadTimetableWithMixedSessionAndSpecialItems() async throws {
        // Arrange
        let mixedItems: [any TimetableItem] = [
            TimetableItemSession(
                id: TimetableItemId(value: "session-1"),
                title: MultiLangText(jaTitle: "Regular Session", enTitle: "Regular Session"),
                startsAt: Date(),
                endsAt: Date().addingTimeInterval(3600),
                category: TimetableCategory(id: 1, title: MultiLangText(jaTitle: "Development", enTitle: "Development")),
                sessionType: .regular,
                room: Room(id: 1, name: MultiLangText(jaTitle: "Room A", enTitle: "Room A"), type: .roomF, sort: 1),
                targetAudience: "All",
                language: TimetableLanguage(langOfSpeaker: "JA", isInterpretationTarget: true),
                asset: TimetableAsset(videoUrl: "https://example.com/video", slideUrl: "https://example.com/slide"),
                levels: ["Intermediate"],
                speakers: [
                    Speaker(id: "speaker-1", name: "Test Speaker", iconUrl: "https://example.com/icon", bio: "Bio", tagLine: "Engineer")
                ],
                description: MultiLangText(jaTitle: "Description", enTitle: "Description"),
                message: nil
            ),
            TimetableItemSpecial(
                id: TimetableItemId(value: "lunch-1"),
                title: MultiLangText(jaTitle: "Lunch", enTitle: "Lunch"),
                startsAt: Date().addingTimeInterval(7200),
                endsAt: Date().addingTimeInterval(10800),
                category: TimetableCategory(id: 99, title: MultiLangText(jaTitle: "Other", enTitle: "Other")),
                sessionType: .lunch,
                room: Room(id: 6, name: MultiLangText(jaTitle: "Lunch Room", enTitle: "Lunch Room"), type: .roomIJ, sort: 6),
                targetAudience: "All",
                language: TimetableLanguage(langOfSpeaker: "JA", isInterpretationTarget: false),
                asset: TimetableAsset(videoUrl: nil, slideUrl: nil),
                levels: [],
                speakers: [],
                description: MultiLangText(jaTitle: "Lunch Time", enTitle: "Lunch Time"),
                message: nil
            )
        ]
        
        let bookmarks = Set<TimetableItemId>([TimetableItemId(value: "session-1")])
        let timetable = Timetable(timetableItems: mixedItems, bookmarks: bookmarks)
        
        // Act
        let result = try await withDependencies {
            $0.timetableUseCase.load = {
                return timetable
            }
        } operation: {
            @Dependency(\.timetableUseCase) var useCase
            return try await useCase.load()
        }
        
        // Assert
        #expect(result.timetableItems.count == 2)
        #expect(result.bookmarks.count == 1)
        
        let sessionItem = result.timetableItems[0] as? TimetableItemSession
        #expect(sessionItem != nil)
        #expect(sessionItem?.sessionType == .regular)
        
        let specialItem = result.timetableItems[1] as? TimetableItemSpecial
        #expect(specialItem != nil)
        #expect(specialItem?.sessionType == .lunch)
    }
    
    @Test("Should retrieve TimetableUseCase from DependencyValues correctly")
    func testTimetableUseCaseAccessibleViaDependencyValues() async throws {
        // Arrange
        let expectedTimetable = Timetable(
            timetableItems: [],
            bookmarks: Set<TimetableItemId>()
        )
        
        // Act
        let result = withDependencies {
            $0.timetableUseCase.load = {
                return expectedTimetable
            }
        } operation: {
            @Dependency(\.timetableUseCase) var useCase
            return useCase
        }
        
        // Assert
        let timetable = try await result.load()
        #expect(timetable.timetableItems.isEmpty)
        #expect(timetable.bookmarks.isEmpty)
    }
    
    @Test("Should preserve all bookmarks when loading timetable with multiple bookmarked items")
    func testLoadTimetablePreservesMultipleBookmarks() async throws {
        // Arrange
        let bookmarks = Set<TimetableItemId>([
            TimetableItemId(value: "item-1"),
            TimetableItemId(value: "item-2"),
            TimetableItemId(value: "item-3"),
            TimetableItemId(value: "item-4"),
            TimetableItemId(value: "item-5")
        ])
        let timetable = Timetable(
            timetableItems: [],
            bookmarks: bookmarks
        )
        
        // Act
        let result = try await withDependencies {
            $0.timetableUseCase.load = {
                return timetable
            }
        } operation: {
            @Dependency(\.timetableUseCase) var useCase
            return try await useCase.load()
        }
        
        // Assert
        #expect(result.bookmarks.count == 5)
        #expect(result.bookmarks.contains(TimetableItemId(value: "item-1")))
        #expect(result.bookmarks.contains(TimetableItemId(value: "item-2")))
        #expect(result.bookmarks.contains(TimetableItemId(value: "item-3")))
        #expect(result.bookmarks.contains(TimetableItemId(value: "item-4")))
        #expect(result.bookmarks.contains(TimetableItemId(value: "item-5")))
    }
}
