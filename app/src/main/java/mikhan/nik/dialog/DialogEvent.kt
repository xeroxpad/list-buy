package mikhan.nik.dialog

sealed class DialogEvent {
    data class OnTextChange(val text: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()
}