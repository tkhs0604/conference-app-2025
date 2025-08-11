import SwiftUI
import simd

struct CardRotation {
    let angle: Angle
    let axis: (x: CGFloat, y: CGFloat, z: CGFloat)
    
    var normal: (Double, Double, Double) {
        let normal = simd_float3(0, 0, 1)
        let axis = simd_normalize(simd_float3(Float(axis.x), Float(axis.y), Float(axis.z)))
        let radians = Float(angle.radians)
        
        let q = simd_quatf(angle: radians, axis: axis)
        let rotatedNormal = q.act(normal)
        return (Double(rotatedNormal.x), Double(rotatedNormal.y), Double(rotatedNormal.z))
    }
}

extension View {
    func kiraEffect(function: ShaderFunction, normal: (Double, Double, Double), monochromeImage: Image) -> some View {
        self.layerEffect(
            Shader(
                function: function,
                arguments: [.boundingRect, .float3(normal.0, normal.1, normal.2), .image(monochromeImage)],
            ),
            maxSampleOffset: .zero
        )
    }
}
