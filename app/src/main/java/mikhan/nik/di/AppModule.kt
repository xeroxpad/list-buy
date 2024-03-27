package mikhan.nik.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mikhan.nik.data.AddItemRepoImplementation
import mikhan.nik.data.AddItemRepository
import mikhan.nik.data.LocalDb
import mikhan.nik.data.NoteRepoImplementation
import mikhan.nik.data.NoteRepository
import mikhan.nik.data.ShoppingListRepoImplementation
import mikhan.nik.data.ShoppingListRepository
import mikhan.nik.datastore.DataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalDb(app: Application): LocalDb {
        return Room.databaseBuilder(
            app,
            LocalDb::class.java,
            "shop_list_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShopRepo(db: LocalDb): ShoppingListRepository {
        return ShoppingListRepoImplementation(db.shoppingListDao)
    }

    @Provides
    @Singleton
    fun provideAddItemRepo(db: LocalDb): AddItemRepository {
        return AddItemRepoImplementation(db.addItemDao)
    }

    @Provides
    @Singleton
    fun provideNoteItemRepo(db: LocalDb): NoteRepository {
        return NoteRepoImplementation(db.noteItemDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(app: Application): DataStoreManager {
        return DataStoreManager(app)
    }
}