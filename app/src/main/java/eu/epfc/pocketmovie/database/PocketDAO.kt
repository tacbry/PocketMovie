package eu.epfc.pocketmovie.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.epfc.pocketmovie.network.Movie

@Dao
interface PocketDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPocket(item: PocketItem)

    @Query("SELECT * from PocketItem")
    suspend fun getAllPocket() : List<PocketItem>

    @Delete
    suspend fun deletePocket(item: PocketItem)
    @Query("SELECT EXISTS(SELECT * FROM PocketItem WHERE movieId = :movieId)")
    suspend fun isMovieInDb(movieId: Int): Boolean
}