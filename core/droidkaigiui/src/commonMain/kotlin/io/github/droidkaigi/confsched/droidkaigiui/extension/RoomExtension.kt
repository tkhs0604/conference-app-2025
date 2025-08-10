package io.github.droidkaigi.confsched.droidkaigiui.extension

import io.github.droidkaigi.confsched.model.core.RoomIcon
import io.github.droidkaigi.confsched.designsystem.theme.RoomTheme
import io.github.droidkaigi.confsched.droidkaigiui.toResDrawable
import io.github.droidkaigi.confsched.model.core.RoomType
import io.github.droidkaigi.confsched.model.core.Room
import org.jetbrains.compose.resources.DrawableResource

val Room.icon: DrawableResource?
    get() = when (type) {
        RoomType.RoomF -> RoomIcon.Rhombus
        RoomType.RoomG -> RoomIcon.Circle
        RoomType.RoomH -> RoomIcon.Diamond
        RoomType.RoomI -> RoomIcon.Square
        RoomType.RoomJ -> RoomIcon.Triangle
        RoomType.RoomIJ -> RoomIcon.None
    }.toResDrawable()

val Room.roomTheme: RoomTheme
    get() = when (type) {
        RoomType.RoomF -> RoomTheme.Flamingo
        RoomType.RoomG -> RoomTheme.Giraffe
        RoomType.RoomH -> RoomTheme.Hedgehog
        RoomType.RoomI -> RoomTheme.Iguana
        RoomType.RoomJ -> RoomTheme.Jellyfish
        RoomType.RoomIJ -> RoomTheme.Iguana
    }
