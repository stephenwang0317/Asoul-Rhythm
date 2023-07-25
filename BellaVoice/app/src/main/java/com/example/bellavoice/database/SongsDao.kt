package com.example.bellavoice.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
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

    @Query("SELECT COUNT(*) FROM songs WHERE path=:path")
    suspend fun checkIfExist(path: String): Int

    @Query("SELECT * FROM songs WHERE id=:id")
    suspend fun getSongById(id: Int): SongBean

    @Upsert
    suspend fun upsertSong(item: SongBean)
}