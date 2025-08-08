# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the iOS app for DroidKaigi 2025 conference, part of a multi-platform project that includes Android, iOS, and Desktop applications. The iOS app is built with modern Swift 6 and SwiftUI, using a clean modular architecture.

## Build and Development Commands

### Building the App
```bash
# Build the main app
xcodebuild build -project DroidKaigi2025.xcodeproj -scheme DroidKaigi2025 -configuration Debug

# Build for release
xcodebuild build -project DroidKaigi2025.xcodeproj -scheme DroidKaigi2025 -configuration Release

# Build specific packages
cd Core && swift build
cd Native && swift build
```

### Running Tests
```bash
# Run component tests
xcodebuild test -project DroidKaigi2025.xcodeproj -scheme ComponentTests -destination 'platform=iOS Simulator,name=iPhone 15 Pro'

# Run use case tests
xcodebuild test -project DroidKaigi2025.xcodeproj -scheme UseCaseTests -destination 'platform=iOS Simulator,name=iPhone 15 Pro'

# Run package tests
cd Core && swift test
cd Native && swift test

# Run Core Package tests on Ubuntu (cross-platform support)
cd Core && swift test  # Works on Ubuntu/Linux environments
```

### Available Schemes
- `DroidKaigi2025` - Main app
- `ComponentTests` - UI component tests
- `Model` - Data model library
- `Presentation` - Presentation layer
- `Root` - Root navigation
- `UseCaseTests` - Business logic tests

## Architecture

The iOS app follows a clean architecture with clear separation between layers:

### Package Structure
- **Core Package** (`Core/`): Business logic separated from platform-specific code
  - `Model/`: Data models and domain entities
  - `Presentation/`: Presenters handling business logic
  - `UseCase/`: Use cases for specific business operations
  
- **Native Package** (`Native/`): iOS-specific implementation
  - `Component/`: Reusable UI components
  - `Feature/`: Feature modules (Home, Timetable, etc.)
  - `KMPFramework/`: Integration with Kotlin Multiplatform shared code
  - `Root/`: Root navigation and app initialization
  - `Theme/`: App theming and styling

### Key Architectural Patterns
1. **Dependency Injection**: Uses Point-Free's swift-dependencies library
2. **Presenter Pattern**: Each feature has a presenter handling business logic
3. **SwiftUI**: Modern declarative UI with iOS 18+ features
4. **Modular Design**: Features are isolated in separate modules
5. **KMP Integration**: Shares business logic with Android via Kotlin Multiplatform

### Testing Strategy
- Uses Apple's new Swift Testing framework (not XCTest)
- Test doubles via `withDependencies` for dependency injection
- Separate test targets for different layers
- **Cross-platform testing**: Core Package tests can run on Ubuntu/Linux environments, enabling CI/CD on non-macOS platforms

## Technical Requirements
- **Swift Version**: 6.1.2
- **Minimum iOS**: 18.0
- **Minimum macOS**: 15.0
- **Swift Language Mode**: Swift 6 (strict concurrency)

## Key Dependencies
- swift-dependencies (1.9.2) - Dependency injection
- KMP shared.xcframework - Shared Kotlin Multiplatform code

## Current Features
- Timetable display (list and grid views)
- Favorites functionality
- Speaker information
- Room management
- Home screen with conference information

## Development Notes
- The project is part of a larger multi-platform repository
- Android app documentation in root README.md shows architectural decisions that may influence iOS development
- No linting tools currently configured for iOS
- Uses modern Swift 6 features including strict concurrency checking