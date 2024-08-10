package star.kiko13bb.emerald.ui.components

import androidx.compose.runtime.Composable
import star.kiko13bb.emerald.R

val icons = listOf(R.drawable.settings_outline_firebase, R.drawable.settings_outline_agency)
val titles = listOf(R.string.settings_title_firebase, R.string.settings_title_agency)
val subtitles = listOf(R.string.settings_subtitle_firebase, R.string.settings_subtitle_agency)
val dialogs: List<@Composable () -> Unit> = listOf( {FirebaseDialog() }, { AgencyDialog() })
