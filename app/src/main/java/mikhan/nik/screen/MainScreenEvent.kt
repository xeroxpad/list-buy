package mikhan.nik.screen

sealed class MainScreenEvent {
    object OnItemSave: MainScreenEvent()
    data class Navigate (val route: String): MainScreenEvent()
    data class NavigateMain (val route: String): MainScreenEvent()
    data class OnNewItemClick (val route: String): MainScreenEvent()
}