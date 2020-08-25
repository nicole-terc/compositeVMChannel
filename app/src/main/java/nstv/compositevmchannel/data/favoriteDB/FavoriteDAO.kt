package nstv.compositevmchannel.data.favoriteDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import nstv.compositevmchannel.data.model.Favorite

@Dao
interface FavoriteDAO {
    @Insert(onConflict = IGNORE)
    suspend fun save(favorite: Favorite)

    @Insert(onConflict = IGNORE)
    suspend fun save(favorites: List<Favorite>)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("SELECT * from favorite")
    suspend fun getAll(): List<Favorite>
}
