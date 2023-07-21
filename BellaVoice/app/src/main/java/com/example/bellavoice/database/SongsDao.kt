package com.example.bellavoice.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bellavoice.model.SongBean

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs ORDER BY id")
    fun getAllOrderById(): LiveData<List<SongBean>>

    @Query("SELECT * FROM songs ORDER BY singer")
    fun getAllOrderBySinger(): LiveData<List<SongBean>>

    @Query("SELECT * FROM songs ORDER BY song")
    fun getAllOrderBySong(): LiveData<List<SongBean>>

    @Delete
    suspend fun delete(item: SongBean)

    @Insert
    suspend fun insert(item: SongBean)
}