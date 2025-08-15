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

## Code Quality Tools

### SwiftLint
- Integrated as SPM build plugin - runs automatically during build
- Configuration: `.swiftlint.yml`
- Manual run: `make lint` or `swiftlint lint --config .swiftlint.yml`
- Auto-fix: `make lint-fix` or `swiftlint lint --config .swiftlint.yml --fix`

### swift-format
- Integrated as SPM dependency
- Configuration: `.swift-format`
- Format code: `make format`
- Check format: `make format-check`

### Pre-commit Hooks
Install git hooks: `./Scripts/install-hooks.sh`
- Automatically runs SwiftLint and swift-format checks on staged files

### Makefile Commands
- `make help` - Show all available commands
- `make setup` - Initial project setup
- `make build` - Build all packages
- `make test` - Run all tests
- `make lint` - Run SwiftLint
- `make lint-fix` - Run SwiftLint with auto-correction
- `make format` - Format code with swift-format
- `make format-check` - Check code formatting
- `make pre-commit` - Run all checks before committing
- `make clean` - Clean build artifacts

## Development Notes
- The project is part of a larger multi-platform repository
- Android app documentation in root README.md shows architectural decisions that may influence iOS development
- **Linting and formatting tools are now configured:**
  - SwiftLint runs automatically during builds via SPM plugin
  - swift-format available for consistent code formatting
  - Pre-commit hooks ensure code quality before commits
- Uses modern Swift 6 features including strict concurrency checking

## Important Build and Debug Notes
### Build Issues
- **Swift Dependencies Macro Error**: The project may encounter macro validation errors with swift-dependencies package when building. This is a known issue with the package itself, not your code changes.
  - Error: "cannot load module 'SwiftDiagnostics' built with SDK 'macosx15.5' when using SDK 'iphonesimulator18.5'"
  - **Workaround**: Open the project in Xcode and build from there, which handles the macro plugin correctly
  - Alternative: Build the app without the DependenciesMacrosPlugin target by commenting it out temporarily
  - The app binary installed on simulator may still work despite build errors

### Debugging
- **Use Mobile MCP for debugging**: When testing UI changes and functionality on iOS simulators, use Mobile MCP tools instead of relying solely on build success
  - `mobile_launch_app`, `mobile_take_screenshot`, `mobile_list_elements_on_screen` etc.
  - This allows verification of UI changes even when build has macro-related errors

### Code Quality Checks
- **Always run lint/format/test before completing work**:
  - `make lint` - Check for linting issues
  - `make lint-fix` - Auto-fix linting issues
  - `make format` - Format code with swift-format
  - `make test` - Run all tests
  - Ensure all checks pass and fixes are applied before considering work complete

### KMP Integration
- **Sponsor data integration**: Currently using mock data in `SponsorUseCaseImpl` as KMP doesn't have sponsorsRepository yet
  - When KMP adds sponsor repository, update `SponsorUseCaseImpl.load()` to use actual data
  - Follow the pattern used in `TimetableUseCaseImpl` for reference