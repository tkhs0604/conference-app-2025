package io.github.droidkaigi.confsched.model.sessions

import io.github.droidkaigi.confsched.model.core.MultiLangText
import kotlinx.serialization.Serializable

@Serializable
data class TimetableCategory(
    val id: Int,
    val title: MultiLangText,
) {
    companion object
}

fun TimetableCategory.Companion.fakes(): List<TimetableCategory> {
    return listOf(
        TimetableCategory(
            id = 1,
            title = MultiLangText(
                jaTitle = "Kotlin",
                enTitle = "Kotlin",
            ),
        ),
        TimetableCategory(
            id = 2,
            title = MultiLangText(
                jaTitle = "Security / Identity / Privacy",
                enTitle = "Security / Identity / Privacy",
            ),
        ),
        TimetableCategory(
            id = 3,
            title = MultiLangText(
                jaTitle = "UI・UX・デザイン",
                enTitle = "UI・UX・Design",
            ),
        ),
    )
}
