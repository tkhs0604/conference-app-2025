package io.github.droidkaigi.confsched.model.contributors

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

data class Contributor(
    val id: Int,
    val username: String,
    val profileUrl: String,
    val iconUrl: String,
) {
    companion object
}

fun Contributor.Companion.fakes(): PersistentList<Contributor> = (1..20)
    .map {
        Contributor(
            id = it,
            username = "user$it",
            profileUrl = "https://developer.android.com/",
            iconUrl = "https://placehold.jp/150x150.png",
        )
    }
    .toPersistentList()
