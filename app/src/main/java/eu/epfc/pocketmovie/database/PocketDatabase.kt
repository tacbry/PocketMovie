package eu.epfc.pocketmovie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import eu.epfc.pocketmovie.network.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class PocketDatabase : RoomDatabase() {
    abstract fun theDAO():PocketDAO
    companion object{
        private const val DATABASE_NAME = "pocket_db"
        private var sInstance : PocketDatabase? = null
        fun getInstance(context : Context):PocketDatabase{
            if(sInstance==null){
                val dbBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    PocketDatabase::class.java, DATABASE_NAME)
                sInstance=dbBuilder.build()
            }
            return sInstance!!
        }
    }
}