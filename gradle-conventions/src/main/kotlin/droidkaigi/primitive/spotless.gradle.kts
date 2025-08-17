package droidkaigi.primitive

import com.diffplug.gradle.spotless.SpotlessExtension
import util.libs
import util.version

plugins {
    id("com.diffplug.spotless")
}

configure<SpotlessExtension> {
    kotlin {
        target("src/**/*.kt")
        ktlint(libs.version("ktlint"))
    }
}
