import SwiftUI
import Theme

public struct DashedDivider: View {
    let axis: Axis

    public init(axis: Axis = .horizontal) {
        self.axis = axis
    }

    public var body: some View {
        let shape = LineShape(axis: axis)
            .stroke(style: StrokeStyle(dash: [2]))
            .foregroundStyle(AssetColors.outlineVariant.swiftUIColor)
        if axis == .horizontal {
            shape.frame(height: 1)
        } else {
            shape.frame(width: 1)
        }
    }
}

struct LineShape: Shape {
    let axis: Axis

    func path(in rect: CGRect) -> Path {
        var path = Path()
        path.move(to: CGPoint.zero)

        if axis == .horizontal {
            path.addLine(to: CGPoint(x: rect.width, y: 0))
        } else {
            path.addLine(to: CGPoint(x: 0, y: rect.height))
        }

        return path
    }
}
