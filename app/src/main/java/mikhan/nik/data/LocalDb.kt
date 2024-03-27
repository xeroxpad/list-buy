package mikhan.nik.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ShoppingListItem::class,
        AddItem::class,
        NoteItem::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class LocalDb : RoomDatabase() {
    abstract val shoppingListDao: ShoppingListDao
    abstract val noteItemDao: NoteItemDao
    abstract val addItemDao: AddItemDao
}