package mikhan.nik.settings_screen

sealed class SettingsEvent {
    data class OnItemSelected (val color: String): SettingsEvent()
}