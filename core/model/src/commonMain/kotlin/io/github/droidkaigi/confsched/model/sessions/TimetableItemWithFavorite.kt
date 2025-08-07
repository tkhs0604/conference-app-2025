package io.github.droidkaigi.confsched.model.sessions

data class TimetableItemWithFavorite(
    val timetableItem: TimetableItem,
    val isFavorited: Boolean,
) {
    companion object
}

fun TimetableItemWithFavorite.Companion.fake(): TimetableItemWithFavorite {
    return TimetableItemWithFavorite(TimetableItem.Session.fake(), true)
}
