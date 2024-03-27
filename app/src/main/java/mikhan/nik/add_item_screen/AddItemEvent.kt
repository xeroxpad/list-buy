package mikhan.nik.add_item_screen

import mikhan.nik.data.AddItem
import mikhan.nik.shopping_list_screen.ShoppingListEvent

sealed class AddItemEvent {
    data class OnDelete (val item: AddItem): AddItemEvent()
    data class OnShowEditDialog(val item: AddItem): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: AddItem): AddItemEvent()
    object OnItemsSave: AddItemEvent()
}