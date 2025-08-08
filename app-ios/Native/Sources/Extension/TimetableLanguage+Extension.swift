import Model

extension TimetableLanguage {
    public var displayLanguage: String {
        var components: [String] = []

        // Language of speaker
        components.append(langOfSpeaker)

        // Add interpretation indicator if needed
        if isInterpretationTarget {
            components.append("(w/ Interpretation)")
        }

        return components.joined(separator: " ")
    }
}
