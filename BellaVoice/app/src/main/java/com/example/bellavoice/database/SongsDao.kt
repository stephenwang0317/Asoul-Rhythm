package com.example.bellavoice.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bellavoice.model.SongBean
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs ORDER BY id")
    fun getAllOrderById(): Flow<List<SongBean>>

    @Query("SELECT * FROM songs ORDER BY singer")
    fun getAllOrderBySinger(): Flow<List<SongBean>>

    @Query("SELECT * FROM songs ORDER BY song")
    fun getAllOrderBySong(): Flow<List<SongBean>>

    @Delete
    suspend fun delete(item: SongBean)

    @Query("DELETE FROM songs WHERE 1=1")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(item: SongBean)
}