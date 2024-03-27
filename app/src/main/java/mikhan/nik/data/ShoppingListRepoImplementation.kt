package mikhan.nik.data

import kotlinx.coroutines.flow.Flow

class ShoppingListRepoImplementation(
    private val dao: ShoppingListDao
): ShoppingListRepository {
    override suspend fun insertItem(item: ShoppingListItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingListItem) {
        dao.deleteShoppingList(item)
    }

    override fun getAllItems(): Flow<List<ShoppingListItem>> {
        return dao.getAllItems()
    }
}