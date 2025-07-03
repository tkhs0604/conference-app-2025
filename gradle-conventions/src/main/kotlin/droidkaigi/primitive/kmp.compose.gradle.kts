package droidkaigi.primitive

import org.gradle.internal.extensions.stdlib.capitalized
import util.commonMainImplementation
import util.getDefaultPackageName
import util.kotlin
import util.library
import util.libs

plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    commonMainImplementation(compose.ui)
    commonMainImplementation(compose.material3)
    commonMainImplementation(compose.components.uiToolingPreview)
    commonMainImplementation(compose.components.resources)
    commonMainImplementation(compose.materialIconsExtended)
    commonMainImplementation(libs.library("rin"))
    commonMainImplementation(libs.library("navigation3Ui"))
}

compose {
    resources {
        val namespace = getDefaultPackageName(project.name)
        generateResClass = always
        packageOfResClass = namespace
        nameOfResClass = namespace.split(".").last().capitalized() + "Res"
    }
}

dependencies {
    add("debugImplementation", compose.uiTooling)
}
