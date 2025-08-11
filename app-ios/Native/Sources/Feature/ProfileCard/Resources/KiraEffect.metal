#include <metal_stdlib>
#include <SwiftUI/SwiftUI_Metal.h>
using namespace metal;

// NOTE: https://zenn.dev/ikeh1024/articles/cc51846dfad295#modとfmodの差異
template<typename Tx, typename Ty>
inline Tx mod(Tx x, Ty y)
{
    return x - y * floor(x / y);
}

// ref: https://github.com/sawa-zen/looking-glass-webxr-study/blob/main/src/HolographicMaterial/fragment.glsl

float generateRandomFloat(float2 st) {
    return fract(sin(dot(st.xy, float2(12.9898, 78.233))) * 43758.5453123);
}

float generateValueNoise(float2 st) {
    float2 i = floor(st);
    float2 f = fract(st);
    
    float a = generateRandomFloat(a);
    float b = generateRandomFloat(i + float2(1.0, 0.0));
    float c = generateRandomFloat(i + float2(0.0, 1.0));
    float d = generateRandomFloat(i + float2(1.0, 1.0));
    
    float2 u = f * f * (3.0 - 2.0 * f);
    
    return mix(a, b, u.x) + (c - a) * u.y * (1.0 - u.x) + (d - b) * u.x * u.y;
}

float3 hsv2rgb(float3 hsv) {
    float4 K = float4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    float3 p = abs(fract(hsv.xxx + K.xyz) * 6.0 - K.www);
    return hsv.z * (K.xxx, clamp(p - K.xxx, 0.0, 1.0), hsv.y);
}

float3 rgb2hsv(float3 rgb) {
    float4 K = float4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    float4 p = mix(float4(rgb.bg, K.wz), float4(rgb.gb, K.xy), step(rgb.b, rgb.g));
    float4 q = mix(float4(p.xyw, rgb.r), float4(rgb.r, p.yzx), step(p.x, rgb.r));
    
    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return float3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

float getViewAngle(float3 normal) {
    float3 faceNormal = normalize(normal);
    float3 lightDir = normalize(float3(0.0, -1.0, -1.0));
    float angle = acos(dot(faceNormal, lightDir));
    return angle;
}

float3 generateAngleRGB(float strength, float3 normal) {
    float pi = 3.141592653589793;
    float angle = mod(getViewAngle(normal) * strength, pi) / pi;
    float3 colorHSV = float3(angle, 1.0, 1.0);
    return hsv2rgb(colorHSV);
}

float3 generateKiraRGB(float3 colorNoiseRGB, float3 normal) {
    float3 angleColorRGB = generateAngleRGB(10.0, normal);
    float colorDiff = distance(colorNoiseRGB, angleColorRGB);
    float3 kiraNoiseHSV = rgb2hsv(colorNoiseRGB);
    kiraNoiseHSV.z = max(1.0 - colorDiff, 0.0);
    return hsv2rgb(kiraNoiseHSV);
}

[[ stitchable ]] half4 kiraEffect(float2 position, SwiftUI::Layer layer, float4 bounds, float3 normal, texture2d<half> monoTexture) {
    float2 uv = (position - .5 * bounds.zw) / bounds.w * float2(1.0, -1.0) + float2(0.0, 1.0);
    
    float2 pos = float2(uv * 6.0);
    float valueNoise = generateValueNoise(pos);
    float3 colorNoiseHSV = float3(valueNoise, 1.0, 1.0);
    constexpr sampler imageSampler(address::clamp_to_edge,
                                   filter::linear);
    float3 kiraNoiseRGB = generateKiraRGB(hsv2rgb(colorNoiseHSV), normal) * float3(monoTexture.sample(imageSampler, position).rgb);
    
    return half4(half3(kiraNoiseRGB) + layer.sample(position).rgb, 1.0);
}
