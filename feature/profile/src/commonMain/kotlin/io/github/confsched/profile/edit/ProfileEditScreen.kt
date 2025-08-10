package io.github.confsched.profile.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.designsystem.theme.ProfileTheme
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.model.profile.Profile
import io.github.droidkaigi.confsched.profile.ProfileRes
import io.github.droidkaigi.confsched.profile.enter_validate_format
import io.github.droidkaigi.confsched.profile.link
import io.github.droidkaigi.confsched.profile.nickname
import io.github.droidkaigi.confsched.profile.occupation
import io.github.droidkaigi.confsched.profile.profile_card_title
import io.github.droidkaigi.confsched.profile.url_is_invalid
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import soil.form.FieldValidator
import soil.form.compose.Field
import soil.form.compose.Form
import soil.form.compose.FormField
import soil.form.compose.hasError
import soil.form.compose.rememberForm
import soil.form.rule.match
import soil.form.rule.notBlank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    form: Form<Profile>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(ProfileRes.string.profile_card_title))
                }
            )
        },
        modifier = modifier
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(contentPadding)
                .scrollable(
                    state = rememberScrollableState { delta -> delta },
                    orientation = Orientation.Horizontal,
                )
        ) {
            form.Name()
            form.Occupation()
            form.Link()
            form.Theme()
            Button(
                onClick = { form.handleSubmit() },
            ) {
                Text("Create")
            }
        }
    }
}

@Composable
private fun Form<Profile>.Name() {
    val emptyNameErrorString = stringResource(
        ProfileRes.string.enter_validate_format,
        stringResource(ProfileRes.string.nickname),
    )
    Field(
        selector = { it.name },
        updater = {
            copy(name = it)
        },
        validator = FieldValidator {
            notBlank { emptyNameErrorString }
        },
        render = { field ->
            field.InputField {
                Text("Name")
            }
        }
    )
}

@Composable
private fun Form<Profile>.Occupation() {
    val emptyOccupationErrorString = stringResource(
        ProfileRes.string.enter_validate_format,
        stringResource(ProfileRes.string.occupation),
    )
    Field(
        selector = { it.occupation },
        updater = { copy(occupation = it) },
        validator = FieldValidator {
            notBlank { emptyOccupationErrorString }
        },
        render = { field ->
            field.InputField {
                Text("Occupation")
            }
        }
    )
}

@Composable
private fun Form<Profile>.Link() {
    val emptyLinkErrorString = stringResource(
        ProfileRes.string.enter_validate_format,
        stringResource(ProfileRes.string.link),
    )
    val invalidLinkErrorString = stringResource(
        ProfileRes.string.url_is_invalid,
    )
    val linkPattern = Regex("^(?:https?://)?(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z0-9-]{2,}(?:/\\S*)?$")
    Field(
        selector = { it.link },
        updater = { copy(link = it) },
        validator = FieldValidator {
            notBlank { emptyLinkErrorString }
            match(linkPattern) { invalidLinkErrorString }
        },
        render = { field ->
            field.InputField {
                Text("Link")
            }
        }
    )
}

@Composable
private fun Form<Profile>.Theme() {
    Field(
        selector = { it.theme },
        updater = { copy(theme = it) },
        render = { field ->
            val themes = ProfileTheme.entries.chunked(2)
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                themes.forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        row.forEach { theme ->
                            ProfileThemeImage(
                                isSelected = ProfileTheme.fromName(field.value) == theme,
                                theme = theme,
                                onThemeClick = { field.onValueChange(theme.name) },
                                modifier = Modifier.weight(1.0f),
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun FormField<String>.InputField(
    label: @Composable () -> Unit,
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = label,
        isError = hasError,
        supportingText = {
            if (hasError) {
                Text(
                    text = error.messages.first(),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Composable
private fun ProfileThemeImage(
    isSelected: Boolean,
    theme: ProfileTheme,
    onThemeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedBorderColor = MaterialTheme.colorScheme.surfaceTint
    val painter = rememberVectorPainter(Icons.Default.Check)

    Text(
        text = theme.name,
        color = Color.Black,
        modifier = modifier
            .selectedBorder(isSelected, selectedBorderColor, painter)
            .clip(RoundedCornerShape(2.dp))
            .background(theme.primaryColor)
            .selectable(isSelected) { onThemeClick() }
            .padding(top = 36.dp, start = 30.dp, end = 30.dp, bottom = 36.dp),
    )
}

private fun Modifier.selectedBorder(
    isSelected: Boolean,
    selectedBorderColor: Color,
    vectorPainter: VectorPainter,
): Modifier = if (isSelected) {
    drawWithContent {
        drawRoundRect(
            color = selectedBorderColor,
            size = size,
            cornerRadius = CornerRadius(2.dp.toPx()),
            style = Stroke(8.dp.toPx(), cap = StrokeCap.Round),
        )
        drawContent()
        drawPath(
            color = selectedBorderColor,
            path = Path().apply {
                moveTo(size.width, 0f)
                lineTo(size.width - 44.dp.toPx(), 0f)
                lineTo(size.width, 44.dp.toPx())
            },
        )
        drawCircle(
            color = Color.White,
            center = Offset(size.width - 12.dp.toPx(), 13.dp.toPx()),
            radius = 10.dp.toPx(),
        )
        translate(left = size.width - 20.dp.toPx(), top = 5.dp.toPx()) {
            with(vectorPainter) {
                draw(size = Size(16.dp.toPx(), 16.dp.toPx()))
            }
        }
    }
} else {
    this
}

@Preview
@Composable
private fun ProfileEditScreenPreview() {
    val form = rememberForm(
        initialValue = Profile(
            name = "John Doe",
            occupation = "Software Engineer",
            link = "https://example.com",
            theme = ProfileTheme.Iguana.name,
        ),
        onSubmit = {}
    )
    KaigiPreviewContainer {
        ProfileEditScreen(form = form)
    }
}
