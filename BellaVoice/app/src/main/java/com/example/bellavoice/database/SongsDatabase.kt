package com.example.bellavoice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bellavoice.model.SongBean

@Database(
    entities = [SongBean::class],
    version = 1,
    exportSchema = false
)
abstract class SongsDatabase : RoomDatabase() {
    abstract fun songsDao(): SongsDao

    companion object {
        private var INSTANCE: SongsDatabase? = null

        fun getInstance(context: Context): SongsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context = context,
                        klass = SongsDatabase::class.java,
                        "songs_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}