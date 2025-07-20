package io.github.droidkaigi.confsched.designsystem

import org.jetbrains.compose.resources.DrawableResource

enum class RoomIcon {
    Square,
    Circle,
    Diamond,
    Rhombus,
    Triangle,
    None,
}

fun RoomIcon.toResDrawable(): DrawableResource? = when (this) {
    RoomIcon.Square -> DesignsystemRes.drawable.ic_square
    RoomIcon.Circle -> DesignsystemRes.drawable.ic_circle
    RoomIcon.Diamond -> DesignsystemRes.drawable.ic_diamond
    RoomIcon.Rhombus -> DesignsystemRes.drawable.ic_rhombus
    RoomIcon.Triangle -> DesignsystemRes.drawable.ic_triangle
    RoomIcon.None -> null
}
