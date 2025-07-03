package io.github.droidkaigi.confsched.model.core

enum class Locale {
    JAPAN,
    OTHER,
}

expect fun getDefaultLocale(): Locale
