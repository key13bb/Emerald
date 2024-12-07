package star.kiko13bb.emerald.ui.components

import androidx.compose.runtime.Composable
import star.kiko13bb.emerald.R

val icons = listOf(R.drawable.settings_outline_firebase, R.drawable.settings_outline_agency, R.drawable.settings_outline_metered)
val titles = listOf(R.string.settings_title_firebase, R.string.settings_title_agency, R.string.settings_title_metered)
val subtitles = listOf(R.string.settings_subtitle_firebase, R.string.settings_subtitle_agency, R.string.settings_subtitle_metered)
val dialogs: List<@Composable () -> Unit> = listOf( {FirebaseDialog() }, { AgencyDialog() }, {MeteredDialog()})
