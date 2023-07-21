package com.example.bellavoice.database

import androidx.lifecycle.LiveData
import com.example.bellavoice.model.SongBean

class SongsRepository(private val songsDao: SongsDao) {
    val readAllData: LiveData<List<SongBean>> = songsDao.getAllOrderById()

    suspend fun addSong(item: SongBean) {
        songsDao.insert(item)
    }
    suspend fun deleteSong(item: SongBean) {
        songsDao.delete(item)
    }


}