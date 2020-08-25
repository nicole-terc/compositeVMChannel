package nstv.compositevmchannel.data.favoriteDB

import androidx.room.Database
import androidx.room.RoomDatabase
import nstv.compositevmchannel.data.model.Favorite

const val DB_NAME = "favoritesDataBase"
@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDataBase : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDAO
}
