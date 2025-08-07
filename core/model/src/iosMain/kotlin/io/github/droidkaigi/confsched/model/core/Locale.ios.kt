package io.github.droidkaigi.confsched.model.core

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages

actual fun getDefaultLocale(): Locale {
    return if (NSLocale.preferredLanguages.first().toString().startsWith("ja")) {
        Locale.JAPAN
    } else {
        Locale.OTHER
    }
}
