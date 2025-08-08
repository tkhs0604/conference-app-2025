# Xcode Build Scripts

This directory contains scripts used during Xcode build phases.

## xcode-lint.sh

A build phase script that runs SwiftLint only on Git-modified Swift files for faster incremental builds.

### Features
- Runs SwiftLint only on modified files (staged, unstaged, and untracked)
- Automatically detects SwiftLint installation location
- Filters files to only lint Core/, Native/, and App/ directories
- Excludes build artifacts and generated files

### Setup in Xcode

1. Open your Xcode project
2. Select your app target
3. Go to "Build Phases" tab
4. Click the "+" button and select "New Run Script Phase"
5. Name it "SwiftLint (Modified Files Only)"
6. Add the following script:

```bash
if [ -f "${SRCROOT}/scripts/xcode-lint.sh" ]; then
    "${SRCROOT}/scripts/xcode-lint.sh"
else
    echo "warning: Lint script not found at ${SRCROOT}/scripts/xcode-lint.sh"
fi
```

7. Optionally, drag this phase to run before "Compile Sources" phase

### Configuration

The script automatically detects SwiftLint from these locations (in order):
1. `.nest/bin/swiftlint` - Project's local installation
2. Mint installation - If installed via Mint
3. System PATH - If globally installed
4. Homebrew locations - `/opt/homebrew/bin` or `/usr/local/bin`

### Behavior

- **No modified files**: Script exits successfully with no action
- **Modified files found**: Runs SwiftLint on each modified Swift file
- **Lint violations found**: Build fails with error message
- **SwiftLint not found**: Shows warning but doesn't block build

### Performance

This script significantly improves build times by:
- Only linting files that have changed since last commit
- Skipping unmodified files
- Using `--quiet` flag to reduce output noise

### Troubleshooting

If SwiftLint is not found:
1. Run `make setup` in the project root
2. Or install SwiftLint: `brew install swiftlint`
3. Or use Mint: `mint install realm/SwiftLint`