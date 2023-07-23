package com.example.bellavoice.database

import androidx.lifecycle.LiveData
import com.example.bellavoice.model.SongBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

class SongsRepository(private val songsDao: SongsDao) {

    fun getAllOrderById(): Flow<List<SongBean>> {
        return songsDao.getAllOrderById()
    }

    fun getAllOrderBySinger(): Flow<List<SongBean>> {
        return songsDao.getAllOrderBySinger()
    }

    fun getAllOrderBySong(): Flow<List<SongBean>> {
        return songsDao.getAllOrderBySong()
    }

    suspend fun addSong(item: SongBean) {
        songsDao.insert(item)
    }

    suspend fun deleteAll() {
        songsDao.deleteAll()
    }

    suspend fun deleteSong(item: SongBean) {
        songsDao.delete(item)
    }


}