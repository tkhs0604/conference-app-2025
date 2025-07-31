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

fun Contributor.Companion.fake(id: Int = 1): Contributor {
    return Contributor(
        id = id,
        username = "user $id",
        profileUrl = "https://developer.android.com/",
        iconUrl = "https://placehold.jp/150x150.png",
    )
}

fun Contributor.Companion.fakes(): PersistentList<Contributor> = (1..20)
    .map { fake(it) }
    .toPersistentList()
