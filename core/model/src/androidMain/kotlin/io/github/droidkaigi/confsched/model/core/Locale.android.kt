package io.github.droidkaigi.confsched.model.core

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

actual fun getDefaultLocale(): Locale {
    val applicationLocales = AppCompatDelegate.getApplicationLocales()
    if (!applicationLocales.isEmpty) {
        return if (applicationLocales == LocaleListCompat.forLanguageTags("ja")) {
            Locale.JAPAN
        } else {
            Locale.OTHER
        }
    }

    return if (java.util.Locale.getDefault() == java.util.Locale.JAPAN) {
        Locale.JAPAN
    } else {
        Locale.OTHER
    }
}
