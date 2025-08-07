plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.kmp.ios")
    id("droidkaigi.primitive.kmp.compose.resources")
    id("droidkaigi.primitive.detekt")
}

dependencies {
    commonMainImplementation(libs.material3)
}
