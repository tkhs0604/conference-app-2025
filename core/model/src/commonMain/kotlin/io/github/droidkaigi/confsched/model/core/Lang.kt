package io.github.droidkaigi.confsched.model.core

import io.github.droidkaigi.confsched.model.core.Lang.ENGLISH
import io.github.droidkaigi.confsched.model.core.Lang.JAPANESE
import io.github.droidkaigi.confsched.model.core.Lang.MIXED

enum class Lang(
    val tagName: String,
    val backgroundColor: Long,
) {
    MIXED("MIXED", backgroundColor = 0xFF7056BB),
    JAPANESE("JA", backgroundColor = 0xFF48A8DA),
    ENGLISH("EN", backgroundColor = 0xFF6ACA8F),
}

fun defaultLang(): Lang = if (getDefaultLocale() == Locale.JAPAN) JAPANESE else ENGLISH

fun Lang.secondLang(): Lang? = when (this) {
    MIXED -> null
    JAPANESE -> ENGLISH
    ENGLISH -> JAPANESE
}
