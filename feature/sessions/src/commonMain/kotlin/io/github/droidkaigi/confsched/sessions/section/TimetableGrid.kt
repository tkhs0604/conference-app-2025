package io.github.droidkaigi.confsched.sessions.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun TimetableGridRoot() {
    Row {
        TimetableGridHours()
        Column {
            TimetableGridRooms()
            TimetableGrid {
                TimetableGridItem()
                TimetableGridItem()
                TimetableGridItem()
                TimetableGridItem()
            }
        }
    }
}

@Composable
fun TimetableGridHours() {
    Text("TimetableGridHours")
}

@Composable
fun TimetableGridRooms() {
    Text("TimetableGridRooms")
}

@Composable
fun TimetableGrid(
    content: @Composable ColumnScope.() -> Unit,
) {
    Text("TimetableGrid")
    Column {
        content()
    }
}

@Composable
fun TimetableGridItem() {
    Text("TimetableGridItem")
}

@Preview
@Composable
private fun TimetableGridRootPreview() {
    KaigiPreviewContainer {
        TimetableGridRoot()
    }
}
