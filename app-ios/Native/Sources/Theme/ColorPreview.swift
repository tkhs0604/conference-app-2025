import SwiftUI

private struct ColorPreview: View {
    var color: Color
    var colorName: String
    
    var body: some View {
        VStack {
            Circle()
                .fill(color)
                .frame(width: 30, height: 30)
                .overlay {
                    Circle()
                        .stroke(
                            style: StrokeStyle(
                                lineWidth: 1,
                                dash: color == .clear ? [1, 2] : []
                            )
                        )
                }
            Text(colorName)
                .font(.caption2)
        }
    }
}

private struct ColorWithDim: View {
    var color: Color
    var dim: Color
    var colorName: String
    
    var body: some View {
        VStack {
            HStack {
                Circle()
                    .fill(color)
                    .frame(width: 30, height: 30)
                    .overlay {
                        Circle()
                            .stroke(
                                style: StrokeStyle(
                                    lineWidth: 1,
                                    dash: color == .clear ? [1, 2] : []
                                )
                            )
                    }
                Circle()
                    .fill(dim)
                    .frame(width: 30, height: 30)
                    .overlay {
                        Circle()
                            .stroke(style: StrokeStyle(
                                lineWidth: 1,
                                dash: dim == .clear ? [1, 2] : []
                            )
                        )
                    }
            }
            Text(colorName)
                .font(.caption2)
        }
    }
}

#Preview {
    Grid {
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "Primary"
            )
            ColorPreview(
                color: .clear,
                colorName: "Secondary"
            )
            ColorPreview(
                color: .clear,
                colorName: "Tertiary"
            )
            ColorPreview(
                color: .clear,
                colorName: "Error"
            )
        }
        GridRow {
            ColorPreview(
                color: AssetColors.onPrimary.swiftUIColor,
                colorName: "On Primary"
            )
            ColorPreview(
                color: AssetColors.onSecondary.swiftUIColor,
                colorName: "On Secondary"
            )
            ColorPreview(
                color: AssetColors.onTertiary.swiftUIColor,
                colorName: "On Tertiary"
            )
            ColorPreview(
                color: AssetColors.onError.swiftUIColor,
                colorName: "On Error"
            )
        }
        GridRow {
            ColorPreview(
                color: AssetColors.primaryContainer.swiftUIColor,
                colorName: "Primary Container"
            )
            ColorPreview(
                color: AssetColors.secondaryContainer.swiftUIColor,
                colorName: "Secondary Container"
            )
            ColorPreview(
                color: AssetColors.tertiaryContainer.swiftUIColor,
                colorName: "Tertiary Container"
            )
            ColorPreview(
                color: AssetColors.errorContainer.swiftUIColor,
                colorName: "Error Container"
            )
        }
        GridRow {
            ColorPreview(
                color: AssetColors.onPrimaryContainer.swiftUIColor,
                colorName: "On Primary Container"
            )
            ColorPreview(
                color: AssetColors.onSecondaryContainer.swiftUIColor,
                colorName: "On Secondary Container"
            )
            ColorPreview(
                color: AssetColors.onTertiaryContainer.swiftUIColor,
                colorName: "On Tertiary Container"
            )
            ColorPreview(
                color: AssetColors.onErrorContainer.swiftUIColor,
                colorName: "On Error Container"
            )
        }
        GridRow {
            ColorWithDim(
                color: AssetColors.primaryFixed.swiftUIColor,
                dim: .clear,
                colorName: "Primary Fixed"
            )
            ColorWithDim(
                color: .clear,
                dim: .clear,
                colorName: "Secondary Fixed"
            )
            ColorWithDim(
                color: .clear,
                dim: AssetColors.tertiaryFixedDim.swiftUIColor,
                colorName: "Primary Fixed"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "On Primary Fixed"
            )
            ColorPreview(
                color: .clear,
                colorName: "On Secondary Fixed"
            )
            ColorPreview(
                color: .clear,
                colorName: "On Tertiary Fixed"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "On Primary Fixed V"
            )
            ColorPreview(
                color: .clear,
                colorName: "On Secondary Fixed V"
            )
            ColorPreview(
                color: .clear,
                colorName: "On Tertiary Fixed V"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "Surface Dim"
            )
            ColorPreview(
                color: AssetColors.surface.swiftUIColor,
                colorName: "Surface"
            )
            ColorPreview(
                color: AssetColors.surfaceVariant.swiftUIColor,
                colorName: "Surface V"
            )
            ColorPreview(
                color: .clear,
                colorName: "Surface Bright"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "SC Lowest"
            )
            ColorPreview(
                color: AssetColors.surfaceContainerLow.swiftUIColor,
                colorName: "SC Low"
            )
            ColorPreview(
                color: .clear,
                colorName: "Surface Container"
            )
            ColorPreview(
                color: .clear,
                colorName: "SC High"
            )
            ColorPreview(
                color: .clear,
                colorName: "SC Highest"
            )
        }
        GridRow {
            ColorPreview(
                color: AssetColors.onSurface.swiftUIColor,
                colorName: "On Surface"
            )
            ColorPreview(
                color: AssetColors.onSurfaceVariant.swiftUIColor,
                colorName: "On Surface V"
            )
            ColorPreview(
                color: AssetColors.outline.swiftUIColor,
                colorName: "Outline"
            )
            ColorPreview(
                color: AssetColors.outlineVariant.swiftUIColor,
                colorName: "Outline Variant"
            )
        }
        GridRow {
            ColorPreview(
                color: AssetColors.inverseSurface.swiftUIColor,
                colorName: "Inverse Surface"
            )
            ColorPreview(
                color: AssetColors.inverseOnSurface.swiftUIColor,
                colorName: "Inverse On Surface"
            )
            ColorPreview(
                color: AssetColors.inversePrimary.swiftUIColor,
                colorName: "Inverse Primary"
            )
            ColorPreview(
                color: AssetColors.scrim.swiftUIColor,
                colorName: "Scrim"
            )
            ColorPreview(
                color: .clear,
                colorName: "Shadow"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "Iguana"
            )
            ColorPreview(
                color: .clear,
                colorName: "Hedgehog"
            )
            ColorPreview(
                color: AssetColors.giraffe.swiftUIColor,
                colorName: "Giraffe"
            )
            ColorPreview(
                color: AssetColors.flamingo.swiftUIColor,
                colorName: "Flamingo"
            )
            ColorPreview(
                color: AssetColors.jellyfish.swiftUIColor,
                colorName: "Jellyfish"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "Iguana Container"
            )
            ColorPreview(
                color: .clear,
                colorName: "Hedgehog Container"
            )
            ColorPreview(
                color: .clear,
                colorName: "Giraffe Container"
            )
            ColorPreview(
                color: .clear,
                colorName: "Flamingo Container"
            )
            ColorPreview(
                color: .clear,
                colorName: "Jellyfish Container"
            )
        }
        GridRow {
            ColorPreview(
                color: .clear,
                colorName: "Iguana Dim"
            )
            ColorPreview(
                color: .clear,
                colorName: "Hedgehog Dim"
            )
            ColorPreview(
                color: .clear,
                colorName: "Giraffe Dim"
            )
            ColorPreview(
                color: .clear,
                colorName: "Flamingo Dim"
            )
            ColorPreview(
                color: .clear,
                colorName: "Jellyfish Dim"
            )
        }
    }
    .padding()
    .background(Color(uiColor: UIColor.systemGroupedBackground))
}
