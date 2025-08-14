import SwiftUI

extension View {
    func kiraEffect(function: ShaderFunction, normal: (Float, Float, Float), monochromeImage: Image) -> some View {
        self.layerEffect(
            Shader(
                function: function,
                arguments: [.boundingRect, .float3(normal.0, normal.1, normal.2), .image(monochromeImage)],
            ),
            maxSampleOffset: .zero
        )
    }
}
