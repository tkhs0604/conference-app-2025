package io.github.droidkaigi.confsched.model.core

import kotlinx.serialization.Serializable

@Serializable
data class MultiLangText(
    val jaTitle: String,
    val enTitle: String,
) {
    val currentLangTitle: String get() = getByLang(defaultLang())

    fun getByLang(lang: Lang): String {
        return if (lang == Lang.JAPANESE) {
            jaTitle
        } else {
            enTitle
        }
    }

    companion object
}
