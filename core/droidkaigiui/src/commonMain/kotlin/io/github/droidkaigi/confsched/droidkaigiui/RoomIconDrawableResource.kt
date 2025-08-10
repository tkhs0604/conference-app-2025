package io.github.droidkaigi.confsched.droidkaigiui

import io.github.droidkaigi.confsched.model.core.RoomIcon
import org.jetbrains.compose.resources.DrawableResource

fun RoomIcon.toResDrawable(): DrawableResource? {
    return when (this) {
        RoomIcon.Square -> DroidkaigiuiRes.drawable.ic_square
        RoomIcon.Circle -> DroidkaigiuiRes.drawable.ic_circle
        RoomIcon.Diamond -> DroidkaigiuiRes.drawable.ic_diamond
        RoomIcon.Rhombus -> DroidkaigiuiRes.drawable.ic_rhombus
        RoomIcon.Triangle -> DroidkaigiuiRes.drawable.ic_triangle
        RoomIcon.None -> null
    }
}
