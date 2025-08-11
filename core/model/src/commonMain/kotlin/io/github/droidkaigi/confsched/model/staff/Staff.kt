package io.github.droidkaigi.confsched.model.staff

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

data class Staff(
    val id: String,
    val name: String,
    val icon: String,
    val profileUrl: String?,
) {
    companion object
}

fun Staff.Companion.fakes(): PersistentList<Staff> {
    return (1..20).map {
        Staff(
            id = it.toString(),
            name = "username $it",
            icon = "https://placehold.jp/150x150.png",
            profileUrl = "https://developer.android.com/",
        )
    }.toPersistentList()
}
